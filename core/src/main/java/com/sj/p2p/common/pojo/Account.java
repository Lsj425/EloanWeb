package com.sj.p2p.common.pojo;

import com.sj.p2p.common.util.SysConstant;

import java.math.BigDecimal;

public class Account {
    private Long id;

    private String tradePassword; // 交易密码

    private BigDecimal usableAmount = SysConstant.ZERO; // 可用资金

    private BigDecimal frozenAmount = SysConstant.ZERO; // 冻结资金

    private BigDecimal borrowLimit = SysConstant.INIT_BORROW_LIMIT; // 可借金额

    private Integer version = 0;

    private BigDecimal unreceivedInterest = SysConstant.ZERO; // 待收利息

    private BigDecimal unreceivedPrincipal = SysConstant.ZERO; //

    private BigDecimal unpaidAmount = SysConstant.ZERO;

    private BigDecimal remainBorrowLimit = SysConstant.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public BigDecimal getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(BigDecimal usableAmount) {
        this.usableAmount = usableAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal freezedAmount) {
        this.frozenAmount = freezedAmount;
    }

    public BigDecimal getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(BigDecimal borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigDecimal getUnreceivedInterest() {
        return unreceivedInterest;
    }

    public void setUnreceivedInterest(BigDecimal unreceivedInterest) {
        this.unreceivedInterest = unreceivedInterest;
    }

    public BigDecimal getUnreceivedPrincipal() {
        return unreceivedPrincipal;
    }

    public void setUnreceivedPrincipal(BigDecimal unreceivedPrincipal) {
        this.unreceivedPrincipal = unreceivedPrincipal;
    }

    public BigDecimal getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(BigDecimal unreturnAmount) {
        this.unpaidAmount = unreturnAmount;
    }

    public BigDecimal getRemainBorrowLimit() {
        return remainBorrowLimit;
    }

    public void setRemainBorrowLimit(BigDecimal remainBorrowLimit) {
        this.remainBorrowLimit = remainBorrowLimit;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", tradePassword='" + tradePassword + '\'' +
                ", usableAmount=" + usableAmount +
                ", frozenAmount=" + frozenAmount +
                ", borrowLimit=" + borrowLimit +
                ", version=" + version +
                ", unreceivedInterest=" + unreceivedInterest +
                ", unreceivedPrincipal=" + unreceivedPrincipal +
                ", unpaidAmount=" + unpaidAmount +
                ", remainBorrowLimit=" + remainBorrowLimit +
                '}';
    }
}