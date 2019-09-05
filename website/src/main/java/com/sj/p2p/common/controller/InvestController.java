package com.sj.p2p.common.controller;

import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.business.pojo.BidRequestAuditHistory;
import com.sj.p2p.business.service.IBidRequestHistoryService;
import com.sj.p2p.business.service.IBidRequestService;
import com.sj.p2p.common.pojo.Account;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.query.BidRequestQueryObject;
import com.sj.p2p.common.service.IAccountService;
import com.sj.p2p.common.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class InvestController {

    @Autowired
    private IBidRequestService iBidRequestService;

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IBidRequestHistoryService iBidRequestHistoryService;

    @Autowired
    private IAccountService iAccountService;

    @RequestMapping("invest")
    public String invest(Model model) {

        return "invest";
    }

    /**
     * 投资列表的明细
     *
     * @param bidRequestQueryObject
     * @param model
     * @return
     */
    @RequestMapping("invest_list")
    public String investList(@ModelAttribute("bidRequestQueryObject") BidRequestQueryObject bidRequestQueryObject, Model model) {
        System.err.println("测试前台获取的bidRequestState"+bidRequestQueryObject.getBidRequestState());
        model.addAttribute("pageResult",
                iBidRequestService.queryForPageByState(bidRequestQueryObject));
        return "invest_list";
    }

    @RequestMapping("borrow_info")
    public String borrowInfo(Long id, Model model) {

        BidRequest bidRequest=iBidRequestService.selectById(id);
        UserInfo userInfo=iUserInfoService.getCurrentUserInfo(bidRequest.getCreateUser().getId());
        List<BidRequestAuditHistory> list= iBidRequestHistoryService.selectAllHistoryById(bidRequest.getCreateUser().getId());
        Account account= iAccountService.getCurrentAccount(userInfo.getId());

        model.addAttribute("bidRequest", bidRequest);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("audits",list);
        model.addAttribute("account",account);

        return "borrow_info";
    }
}
