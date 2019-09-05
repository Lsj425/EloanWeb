package com.sj.p2p.common.query;



/**
 * 用户信息查询对象
 *
 * @author novo
 */

public class BidRequestQueryObject extends QueryObject{
    private Integer bidRequestState;

    public Integer getBidRequestState() {
        return bidRequestState;
    }

    public void setBidRequestState(Integer bidRequestState) {
        this.bidRequestState = bidRequestState;
    }
}
