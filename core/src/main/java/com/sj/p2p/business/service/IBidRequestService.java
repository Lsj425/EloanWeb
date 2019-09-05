package com.sj.p2p.business.service;

import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.common.query.BidRequestQueryObject;
import com.sj.p2p.common.query.PageResultSet;

import java.util.List;

public interface IBidRequestService {

    /**
     * 添加投标申请
     *
     * @param bidRequest
     */
    void addBidRequest(BidRequest bidRequest);

    /**
     * 投标信息分页查询
     *
     * @return
     *
     */
    PageResultSet queryForPage(BidRequestQueryObject bidRequestQueryObject);

    /**
     * 投标信息分页条件查询
     * 根据传入的bidRequestState判断要展示什么类型的投标
     * @return
     *
     */
    PageResultSet queryForPageByState(BidRequestQueryObject bidRequestQueryObject);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    BidRequest selectById(Long id);

    /**
     * 根据state查询
     *
     * @return
     */
    List<BidRequest> selectByState();
}
