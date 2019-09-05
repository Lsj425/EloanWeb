package com.sj.p2p.business.pojo;

public class BidRequestAuditHistory extends BaseAuditDomain {

    public static final int PUBLISH_AUDIT = 0; //发标审核
    public static final int FULL_AUDIT1 = 1; //满标一审
    public static final int FULL_AUDIT2 = 2; //满标二审

    private int id;
    private Long bidRequestId;     //关联到对应的 bidRequest
    private int auditType;         //审核的类型

    public String getAuditTypeDisplay() {
        switch (this.auditType) {
            case PUBLISH_AUDIT:
                return "发标审核";
            case FULL_AUDIT1:
                return "满标一审";
            case FULL_AUDIT2:
                return "满标二审";
            default:
                return "";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getBidRequestId() {
        return bidRequestId;
    }

    public void setBidRequestId(Long bidRequestId) {
        this.bidRequestId = bidRequestId;
    }

    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
    }

    @Override
    public String toString() {
        return "BidRequestAuditHistory{" +
                "Id=" + id +
                ", state=" + state +
                ", remark=" + remark +
                ", auditTime=" + auditTime +
                ", applyTime=" + applyTime +
                ", auditor=" + auditor +
                ", applier=" + applier +
                ", bidRequestId=" + bidRequestId +
                ", auditType=" + auditType +
                '}';
    }
}
