package com.sj.p2p.common.controller;

import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.business.pojo.BidRequestAuditHistory;
import com.sj.p2p.business.service.IBidRequestHistoryService;
import com.sj.p2p.business.service.IBidRequestService;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.query.BidRequestQueryObject;
import com.sj.p2p.common.service.IUserInfoService;
import com.sj.p2p.common.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BidRequestController {
    @Autowired
    private IBidRequestService iBidRequestService;

    @Autowired
    private IBidRequestHistoryService iBidRequestHistoryService;

    @Autowired
    private IUserInfoService iUserInfoService;

    /**
     * 投标信息列表
     *
     * @param bidRequestQueryObject
     * @param model
     * @return
     */
    @RequestMapping("bidrequest_publishaudit_list")
    public String bidRequsetList(BidRequestQueryObject bidRequestQueryObject, Model model) {

        model.addAttribute("pageResult", iBidRequestService.queryForPage(bidRequestQueryObject));
        return "bidrequest/publish_audit";
    }

    // 处理审查逻辑
    @RequestMapping("bidrequest_publishaudit")
    @ResponseBody
    public JSONResult bidRequestPublishAudit(BidRequestAuditHistory bidRequestAuditHistory) {
        // 审查后更改userinfo、bidRequest、bidRequestHistory三张表的信息
        iBidRequestHistoryService.audit(bidRequestAuditHistory);
        JSONResult json = new JSONResult();
        json.setSuccess(true);
        return json;
    }

    // 查看借款申请详情
    @RequestMapping("borrow_info")
    public String borrowInfo(Long id, Model model) {

        BidRequest bidRequest=iBidRequestService.selectById(id);
        UserInfo userInfo=iUserInfoService.getCurrentUserInfo(bidRequest.getCreateUser().getId());
        List<BidRequestAuditHistory> list= iBidRequestHistoryService.selectAllHistoryById(bidRequest.getCreateUser().getId());

        model.addAttribute("bidRequest", bidRequest);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("audits",list);

        return "bidrequest/borrow_info";
    }

}
