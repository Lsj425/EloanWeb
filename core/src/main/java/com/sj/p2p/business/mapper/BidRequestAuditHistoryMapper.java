package com.sj.p2p.business.mapper;

import com.sj.p2p.business.pojo.BidRequestAuditHistory;

import java.util.List;

public interface BidRequestAuditHistoryMapper {

    /**
     * 插入一条审查数据
     *
     * @param record
     * @return
     */
    int insert(BidRequestAuditHistory record);

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    BidRequestAuditHistory selectByPrimaryKey(Long id);


    /**
     * 根据RequestId查询
     *
     * @param id
     * @return
     */
    List<BidRequestAuditHistory> selectAllByRequestId(Long id);

    /**
     * 主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(BidRequestAuditHistory record);
}