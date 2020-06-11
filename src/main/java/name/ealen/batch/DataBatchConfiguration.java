package name.ealen.batch;

import name.ealen.Dao.DaySummaryRepository;
import name.ealen.listener.JobListener;
import name.ealen.model.CustTranc;
import name.ealen.model.OutPutDay;
import name.ealen.util.Utiluuid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EalenXie on 2018/9/10 14:50.
 * :@EnableBatchProcessing提供用于构建批处理作业的基本配置
 */
@Configuration
@EnableBatchProcessing
public class DataBatchConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DataBatchConfiguration.class);
    //用于构建JOB
    @Resource
    private JobBuilderFactory jobBuilderFactory;
    //用于构建Step
    @Resource
    private StepBuilderFactory stepBuilderFactory;
    //注入实例化Factory 访问数据
    @Resource
    private EntityManagerFactory emf;
    //简单的JOB listener
    @Resource
    private JobListener jobListener;



    @Resource
    private DaySummaryRepository daySummaryRepository;
    /**
     * 一个简单基础的Job通常由一个或者多个Step组成
     */
    @Bean
    @Scheduled(cron="0 0/3 9-17 * * ?")
    public Job dataHandleJob() {
        Job job =jobBuilderFactory.get("dataHandleJob1").
                incrementer(new RunIdIncrementer()).
                //start是JOB执行的一个step
                        start(handleDataStep()).
                //设置了一个简单JobListener
                        listener(jobListener).
                        build();
        return job;
    }

    /**
     * 一个简单基础的Step主要分为三个部分
     * ItemReader : 用于读取数据
     * ItemProcessor : 用于处理数据
     * ItemWriter : 用于写数据
     */
    @Bean
    public Step handleDataStep() {
        return stepBuilderFactory.get("getData").
                // <输入,输出> 。chunk通俗的讲类似于SQL的commit; 这里表示处理(processor)100条后写入(writer)一次。
                <CustTranc, CustTranc>chunk(100).
                //捕捉到异常就重试,重试100次还是异常,JOB就停止并标志失败
                faultTolerant().retryLimit(3).retry(Exception.class).skipLimit(100).skip(Exception.class).
                //指定ItemReader
                reader(getDataReader()).
                //指定ItemProcessor
                processor(getDataProcessor()).
                //指定ItemWriter
                writer(getDataWriter()).
                build();
    }

    @Bean
    public ItemReader<? extends CustTranc> getDataReader() {
        //读取数据,这里可以用JPA,JDBC,JMS 等方式 读入数据
        JpaPagingItemReader<CustTranc> reader = new JpaPagingItemReader<>();

        //这里选择JPA方式读数据 一个简单的 native SQL
        //select c.cust_id,c.surname,c.gender,c.educa_des,c.mar_des,c.birthday,c.address,t.trans_id,t.account,t.card_nbr,t.tranno,t.month_nbr,t.bill,t.trans_type,t.txn_datetime
        // from customer_info c left join transaction_detail t on c.cust_id = t.cust_id;
        String sqlQuery = "select c.cust_id,c.surname,c.gender,c.educa_des,c.mar_des,c.birthday,c.address,t.trans_id,t.account,t.card_nbr,t.tranno,t.month_nbr,t.bill,t.trans_type,t.txn_datetime from customer_info c left join transaction_detail t on c.cust_id = t.cust_id";
        try {
            JpaNativeQueryProvider<CustTranc> queryProvider = new JpaNativeQueryProvider<>();
            queryProvider.setSqlQuery(sqlQuery);
            queryProvider.setEntityClass(CustTranc.class);
            queryProvider.afterPropertiesSet();
            reader.setEntityManagerFactory(emf);
            reader.setPageSize(3);
            reader.setQueryProvider(queryProvider);
            reader.afterPropertiesSet();
            //所有ItemReader和ItemWriter实现都会在ExecutionContext提交之前将其当前状态存储在其中,如果不希望这样做,可以设置setSaveState(false)
            reader.setSaveState(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reader;
    }

    @Bean
    public ItemProcessor<CustTranc, CustTranc> getDataProcessor() {
        return new ItemProcessor<CustTranc, CustTranc>() {
            @Override
            public CustTranc process(CustTranc access) throws Exception {
                return access;
            }
        };
    }

    @Bean
    public ItemWriter<CustTranc> getDataWriter() {
        return list -> {
            //客户号
            int cust_id = 0;
            //更新时间
            Date nowdate=new Date();
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//          driver.setRelDate(Timestamp.valueOf(simpleDate.format(nowdate)));
            Date update_time = Timestamp.valueOf(simpleDate.format(nowdate));
            //日期
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String nowdayTime = dateFormat.format(nowdate);
            Date trans_date = dateFormat.parse(nowdayTime);
            //客户姓名
            String surname = "";
            //最大单笔交易金额
            BigDecimal tran_max_amt = new BigDecimal(0);
            //当天还款总金额
            BigDecimal pay_amt = new BigDecimal(0);
            //当天消费笔数
            int tran_cnt  = 0;
            //当天还款笔数
            int pay_cnt = 0;
            //当天交易总金额
            BigDecimal tran_amt = new BigDecimal(0);

            String txn_datetime = "";
            for (CustTranc access : list) {
                if(cust_id==access.getCustId()){
                    cust_id=access.getCustId();
                    surname=access.getSurname();
                    trans_date = access.getTxnDatetime();
                    txn_datetime = access.getTxnDatetime().toString().substring(0,10);
                    String dates = access.getTxnDatetime().toString().substring(0,10);
                    if(dates.equals(txn_datetime)){
                        if(tran_max_amt.intValue()<access.getBill().intValue()){
                            tran_max_amt=access.getBill();
                        }
                        if(access.getTransType().equals("还款")){
                            tran_amt=tran_amt.add(access.getBill());
                            pay_amt=pay_amt.add(access.getBill());
                            pay_cnt++;
                        }else {
                            tran_amt=tran_amt.add(access.getBill());
                            tran_cnt++;
                        }
                    }
                }else {
//                        cust_id=access.getCustId();
                        OutPutDay outPutDay = new OutPutDay();
                        outPutDay.setCustId(cust_id);
                        outPutDay.setUpdateTime(update_time);
                        outPutDay.setTransDate(trans_date);
                        outPutDay.setSurname(surname);
                        outPutDay.setTranMaxAmt(tran_max_amt);
                        outPutDay.setTranAmt(tran_amt);
                        outPutDay.setPayCnt(pay_cnt);
                        outPutDay.setPayAmt(pay_amt);
                        outPutDay.setTranCnt(tran_cnt);
                        outPutDay.setsIndex(Utiluuid.getUuid());
//                insert into DaySummaryStatistic (s_index, cust_id, update_time, trans_date, surname, tran_max_amt, pay_amt, tran_cnt, pay_cnt, tran_amt)values (#{sIndex}, #{custId}, #{updateTime}, #{transDate}, #{surname}, #{tranMaxAmt},#{payAmt}, #{tranCnt}, #{payCnt}, #{tranAmt})
                        if(outPutDay.getCustId()!=0){
                            log.info("write data : " + outPutDay.toString());
                            daySummaryRepository.save(outPutDay);
//                            String jpql = "UPDATE Customer c SET c.lastName = ? WHERE c.id = ?";
//                            Quary query = entityManager.createQuery(jpql).setParameter(1, "YYY").setParameter(2, 12);
//
//                            query.executeUpdate();
                        }
//                    daySummaryRepository.save(outPutDay);
//                    day_summarystatisticMapper.insert(outPutDay);
                        cust_id=0;
                        surname="";
                        tran_max_amt=new BigDecimal(0);
                        tran_amt=new BigDecimal(0);
                        pay_amt = new BigDecimal(0);
                        pay_cnt = 0;
                        tran_cnt=0;
                        cust_id=access.getCustId();
                    }
                }

        };
    }


}
