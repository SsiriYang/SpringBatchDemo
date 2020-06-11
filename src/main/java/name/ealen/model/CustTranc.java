package name.ealen.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author 41765
 * @Creater 2020/6/10 22:38
 * Description
 */
@Entity
@Table
public class CustTranc {
    /**
     * 客户号
     */
    private Integer custId;

    /**
     * 客户姓名
     */
    private String surname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 教育状况
     */
    private String educaDes;

    /**
     * 婚姻状况
     */
    private String marDes;

    /**
     * 生日
     */
    private Integer birthday;

    /**
     * 住址
     */
    private String address;

    /**
     * 客户号
     * @return cust_id 客户号
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * 客户号
     * @param custId 客户号
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
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
     * 性别
     * @return gender 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 性别
     * @param gender 性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 教育状况
     * @return educa_des 教育状况
     */
    public String getEducaDes() {
        return educaDes;
    }

    /**
     * 教育状况
     * @param educaDes 教育状况
     */
    public void setEducaDes(String educaDes) {
        this.educaDes = educaDes;
    }

    /**
     * 婚姻状况
     * @return mar_des 婚姻状况
     */
    public String getMarDes() {
        return marDes;
    }

    /**
     * 婚姻状况
     * @param marDes 婚姻状况
     */
    public void setMarDes(String marDes) {
        this.marDes = marDes;
    }

    /**
     * 生日
     * @return birthday 生日
     */
    public Integer getBirthday() {
        return birthday;
    }

    /**
     * 生日
     * @param birthday 生日
     */
    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    /**
     * 住址
     * @return address 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 交易id
     */
    @Id
    private Integer transId;


    /**
     * 账户号
     */
    private String account;

    /**
     * 卡号
     */
    private String cardNbr;

    /**
     * 交易流水号
     */
    private Integer tranno;

    /**
     * 账单月
     */
    private Integer monthNbr;

    /**
     * 交易金额
     */
    private BigDecimal bill;

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 交易日期
     */
    private Date txnDatetime;

    /**
     * 交易id
     * @return trans_id 交易id
     */
    public Integer getTransId() {
        return transId;
    }

    /**
     * 交易id
     * @param transId 交易id
     */
    public void setTransId(Integer transId) {
        this.transId = transId;
    }



    /**
     * 账户号
     * @return account 账户号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账户号
     * @param account 账户号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 卡号
     * @return card_nbr 卡号
     */
    public String getCardNbr() {
        return cardNbr;
    }

    /**
     * 卡号
     * @param cardNbr 卡号
     */
    public void setCardNbr(String cardNbr) {
        this.cardNbr = cardNbr;
    }

    /**
     * 交易流水号
     * @return tranno 交易流水号
     */
    public Integer getTranno() {
        return tranno;
    }

    /**
     * 交易流水号
     * @param tranno 交易流水号
     */
    public void setTranno(Integer tranno) {
        this.tranno = tranno;
    }

    /**
     * 账单月
     * @return month_nbr 账单月
     */
    public Integer getMonthNbr() {
        return monthNbr;
    }

    /**
     * 账单月
     * @param monthNbr 账单月
     */
    public void setMonthNbr(Integer monthNbr) {
        this.monthNbr = monthNbr;
    }

    /**
     * 交易金额
     * @return bill 交易金额
     */
    public BigDecimal getBill() {
        return bill;
    }

    /**
     * 交易金额
     * @param bill 交易金额
     */
    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    /**
     * 交易类型
     * @return trans_type 交易类型
     */
    public String getTransType() {
        return transType;
    }

    /**
     * 交易类型
     * @param transType 交易类型
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * 交易日期
     * @return txn_datetime 交易日期
     */
    public Date getTxnDatetime() {
        return txnDatetime;
    }

    /**
     * 交易日期
     * @param txnDatetime 交易日期
     */
    public void setTxnDatetime(Date txnDatetime) {
        this.txnDatetime = txnDatetime;
    }

}
