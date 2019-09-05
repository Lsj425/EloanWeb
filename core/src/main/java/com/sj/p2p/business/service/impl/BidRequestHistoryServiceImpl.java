package com.sj.p2p.business.service.impl;

import com.sj.p2p.business.mapper.BidRequestAuditHistoryMapper;
import com.sj.p2p.business.mapper.BidRequestMapper;
import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.business.pojo.BidRequestAuditHistory;
import com.sj.p2p.business.service.IBidRequestHistoryService;
import com.sj.p2p.common.mapper.UserInfoMapper;
import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.util.BitStatesUtils;
import com.sj.p2p.common.util.SysConstant;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BidRequestHistoryServiceImpl implements IBidRequestHistoryService {
    @Autowired
    BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

    @Autowired
    private BidRequestMapper bidrequestMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void audit(BidRequestAuditHistory bidRequestAuditHistory) {

        // 更改用户绑定状态码(userInfo表)
        BidRequest bidRequest = bidrequestMapper.selectByPrimaryKey(bidRequestAuditHistory.getBidRequestId());
        LoginInfo applierLoginInfo = bidRequest.getCreateUser();
        if (bidRequestAuditHistory.getState()==2){
            // 审核不通过，移除用户状态码，使其可以继续申请贷款
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(applierLoginInfo.getId());
            userInfo.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS); // 移除用户发标状态码
            userInfoMapper.updateByPrimaryKey(userInfo); //更新数据库userInfo
        }

        // 插入贷款申请审查记录信息(bidrequestaudithistory表)
        LoginInfo auditorLoginInfo = UserContext.getLoginInfo();
        bidRequestAuditHistory.setAuditTime(new Date());
        bidRequestAuditHistory.setApplyTime(bidRequest.getApplyTime());
        bidRequestAuditHistory.setApplier(applierLoginInfo);
        bidRequestAuditHistory.setAuditor(auditorLoginInfo);
        bidRequestAuditHistory.setAuditType(0);
        System.err.println("开始执行插入mapper");
        bidRequestAuditHistoryMapper.insert(bidRequestAuditHistory);
        System.err.println(bidRequestAuditHistory.toString());

        // 更新贷款申请信息（bid_request表）
        if (bidRequestAuditHistory.getState()==1){
            // 审核通过
            bidRequest.setBidRequestState(SysConstant.BIDREQUEST_STATE_BIDDING);// 修改审核通过结果
            Calendar c = Calendar.getInstance();
            c.setTime(bidRequestAuditHistory.getAuditTime());
            c.add(Calendar.MONTH, bidRequest.getMonthes2Return());
            bidRequest.setDisableDate(c.getTime());// 添加招标截止时间
            bidRequest.setPublishTime(bidRequestAuditHistory.getAuditTime()); // 添加招标发布时间
        }else {
            // 审核拒绝
            bidRequest.setBidRequestState(SysConstant.BIDREQUEST_STATE_PUBLISH_REFUSE);// 修改审核拒绝结果
        }
        bidRequest.setNote(bidRequestAuditHistory.getRemark());

        System.err.println(bidRequest.toString());
        bidrequestMapper.updateByPrimaryKey(bidRequest);
    }

    @Override
    public List<BidRequestAuditHistory> selectAllHistoryById(Long id) {

        return bidRequestAuditHistoryMapper.selectAllByRequestId(id);
    }


}
