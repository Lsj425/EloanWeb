package com.sj.p2p.business.service;


import com.sj.p2p.business.pojo.BidRequestAuditHistory;

import java.util.List;

public interface IBidRequestHistoryService {
    /**
     * 审核后更改userinfo、bidRequest、bidRequestHistory三张表的信息
     * @param bidRequestAuditHistory
     */
    void audit(BidRequestAuditHistory bidRequestAuditHistory);

    /**
     * 查询历史审核记录
     *
     * @param id
     * @return
     */
    List<BidRequestAuditHistory> selectAllHistoryById(Long id);
}
