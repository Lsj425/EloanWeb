package com.sj.p2p.business.service.impl;

import com.sj.p2p.business.mapper.BidRequestMapper;
import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.business.service.IBidRequestService;
import com.sj.p2p.business.util.CalculatetUtil;
import com.sj.p2p.common.query.BidRequestQueryObject;
import com.sj.p2p.common.query.PageResultSet;
import com.sj.p2p.common.util.SysConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BidRequestServiceImpl implements IBidRequestService {
    @Autowired
    private BidRequestMapper bidrequestMapper;

    @Override
    public void addBidRequest(BidRequest bidrequest) {
        bidrequest.setBidRequestType(SysConstant.BIDREQUEST_TYPE_NORMAL);
        bidrequest.setBidRequestState(SysConstant.BIDREQUEST_STATE_PUBLISH_PENDING);
//        bidrequest.setBidCount(0);
        bidrequest.setTotalRewardAmount(BigDecimal.ZERO);
//        bidrequest.setCurrentSum(BigDecimal.ZERO);
        bidrequest.setApplyTime(new Date());

        bidrequest.setTotalRewardAmount(
                CalculatetUtil.calTotalInterest(bidrequest.getReturnType(),
                        bidrequest.getBidRequestAmount(),
                        bidrequest.getCurrentRate(),
                        bidrequest.getMonthes2Return()));

        bidrequestMapper.insert(bidrequest);
    }

    /**
     * 历史投标信息分页查询
     *
     * @param bidRequestQueryObject
     * @return
     */
    @Override
    public PageResultSet queryForPage(BidRequestQueryObject bidRequestQueryObject) {

        int count = bidrequestMapper.queryForCount();

        PageResultSet pageResultSet;
        //如果存在符合条件的数据，对数据进行分页查询，获取当前页的数据;没有则返回空的数据集
        if (count > 0) {
            List<BidRequest> list = bidrequestMapper.queryForPage(bidRequestQueryObject);
            pageResultSet = new PageResultSet(
                    list,
                    count,
                    bidRequestQueryObject.getCurrentPage(),
                    bidRequestQueryObject.getPageSize());
        } else {
            pageResultSet = PageResultSet.empty(bidRequestQueryObject.getPageSize());
        }

        return pageResultSet;
    }


    @Override
    public BidRequest selectById(Long id) {
        BidRequest bidRequest=bidrequestMapper.selectByPrimaryKey(id);
        return bidRequest;
    }

    @Override
    public List<BidRequest> selectByState() {
        return bidrequestMapper.selectAllByState();
    }

    @Override
    public PageResultSet queryForPageByState(BidRequestQueryObject bidRequestQueryObject) {
        System.err.println("开始执行queryForCountByState");
        int count = bidrequestMapper.queryForCountByState(bidRequestQueryObject);
        System.err.println("查看查询结果："+count);

        PageResultSet pageResultSet;
        //如果存在符合条件的数据，对数据进行分页查询，获取当前页的数据;没有则返回空的数据集
        if (count > 0) {
            List<BidRequest> list = bidrequestMapper.queryForPageByState(bidRequestQueryObject);
            pageResultSet = new PageResultSet(
                    list,
                    count,
                    bidRequestQueryObject.getCurrentPage(),
                    bidRequestQueryObject.getPageSize());
        } else {
            System.err.println("未查询到数据，sql语句出错");
            pageResultSet = PageResultSet.empty(bidRequestQueryObject.getPageSize());
        }

        return pageResultSet;
    }

}
