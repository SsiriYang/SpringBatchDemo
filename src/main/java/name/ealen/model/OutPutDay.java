package name.ealen.model;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 41765
 * @Creater 2020/6/10 18:46
 * Description
 */
@Entity
@Table(name="day_summarystatistic")
public class OutPutDay extends JpaRepositoriesAutoConfiguration {
    /**
     * 索引项
     */
    @Id
    private String s_index;

    /**
     * 客户号
     */
    private Integer cust_id;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 日期
     */
    private Date trans_date;

    /**
     * 客户姓名
     */
    private String surname;

    /**
     * 最大单笔交易金额
     */
    private BigDecimal tran_max_amt;

    /**
     * 当天还款总金额
     */
    private BigDecimal pay_amt;

    /**
     * 当天消费笔数
     */
    private Integer tran_cnt;

    /**
     * 当天还款笔数
     */
    private Integer pay_cnt;

    /**
     * 当天交易总金额
     */
    private BigDecimal tran_amt;

    /**
     * 索引项
     * @return s_index 索引项
     */
    public String getsIndex() {
        return s_index;
    }

    /**
     * 索引项
     * @param sIndex 索引项
     */
    public void setsIndex(String sIndex) {
        this.s_index = sIndex;
    }

    /**
     * 客户号
     * @return cust_id 客户号
     */
    public Integer getCustId() {
        return cust_id;
    }

    /**
     * 客户号
     * @param custId 客户号
     */
    public void setCustId(Integer custId) {
        this.cust_id = custId;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return update_time;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.update_time = updateTime;
    }

    /**
     * 日期
     * @return trans_date 日期
     */
    public Date getTransDate() {
        return trans_date;
    }

    /**
     * 日期
     * @param transDate 日期
     */
    public void setTransDate(Date transDate) {
        this.trans_date = transDate;
    }

    /**
     * 客户姓名
     * @return surname 客户姓名
     */
    public String getSurname() {
        return surname;
    }

    /**
     * 客户姓名
     * @param surname 客户姓名
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * 最大单笔交易金额
     * @return tran_max_amt 最大单笔交易金额
     */
    public BigDecimal getTranMaxAmt() {
        return tran_max_amt;
    }

    /**
     * 最大单笔交易金额
     * @param tranMaxAmt 最大单笔交易金额
     */
    public void setTranMaxAmt(BigDecimal tranMaxAmt) {
        this.tran_max_amt = tranMaxAmt;
    }

    /**
     * 当天还款总金额
     * @return pay_amt 当天还款总金额
     */
    public BigDecimal getPayAmt() {
        return pay_amt;
    }

    /**
     * 当天还款总金额
     * @param payAmt 当天还款总金额
     */
    public void setPayAmt(BigDecimal payAmt) {
        this.pay_amt = payAmt;
    }

    /**
     * 当天消费笔数
     * @return tran_cnt 当天消费笔数
     */
    public Integer getTranCnt() {
        return tran_cnt;
    }

    /**
     * 当天消费笔数
     * @param tranCnt 当天消费笔数
     */
    public void setTranCnt(Integer tranCnt) {
        this.tran_cnt = tranCnt;
    }

    /**
     * 当天还款笔数
     * @return pay_cnt 当天还款笔数
     */
    public Integer getPayCnt() {
        return pay_cnt;
    }

    /**
     * 当天还款笔数
     * @param payCnt 当天还款笔数
     */
    public void setPayCnt(Integer payCnt) {
        this.pay_cnt = payCnt;
    }

    /**
     * 当天交易总金额
     * @return tran_amt 当天交易总金额
     */
    public BigDecimal getTranAmt() {
        return tran_amt;
    }

    /**
     * 当天交易总金额
     * @param tranAmt 当天交易总金额
     */
    public void setTranAmt(BigDecimal tranAmt) {
        this.tran_amt = tranAmt;
    }

    @Override
    public String toString() {
        return "OutPutDay{" +
                "sIndex='" + s_index + '\'' +
                ", custId=" + cust_id +
                ", updateTime=" + update_time +
                ", transDate=" + trans_date +
                ", surname='" + surname + '\'' +
                ", tranMaxAmt=" + tran_max_amt +
                ", payAmt=" + pay_amt +
                ", tranCnt=" + tran_cnt +
                ", payCnt=" + pay_cnt +
                ", tranAmt=" + tran_amt +
                '}';
    }
}
