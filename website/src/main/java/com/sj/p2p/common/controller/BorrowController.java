package com.sj.p2p.common.controller;

import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.business.service.IBidRequestService;
import com.sj.p2p.common.pojo.Account;
import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.service.IAccountService;
import com.sj.p2p.common.service.IUserInfoService;
import com.sj.p2p.common.util.BitStatesUtils;
import com.sj.p2p.common.util.SysConstant;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BorrowController {
    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IBidRequestService iBidRequestService;

    // 展示借款资格审核页面
    @RequestMapping("borrow")
    public String borrow(Model model) {
        LoginInfo loginInfo = UserContext.getLoginInfo();
        Account currentAccount = iAccountService.getCurrentAccount(loginInfo.getId());
        UserInfo userInfo = iUserInfoService.getCurrentUserInfo(loginInfo.getId());

        model.addAttribute("creditBorrowScore", SysConstant.CREDIT_BORROW_SCORE);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("account", currentAccount);
        return "borrow";
    }

    // 展示借款申请页面
    @RequestMapping("borrowInfo")
    public String borrowInfo(Model model) {
        LoginInfo loginInfo = UserContext.getLoginInfo();
        Account currentAccount = iAccountService.getCurrentAccount(loginInfo.getId());

        model.addAttribute("account", currentAccount);
        model.addAttribute("minBidRequestAmount", SysConstant.SMALLEST_BIDREQUEST_AMOUNT);
        model.addAttribute("minBidAmount", SysConstant.SMALLEST_BID_AMOUNT);

        return "borrow_apply";
    }

    // 处理借款申请表单，并返回处理结果页面
    @RequestMapping("borrow_apply")
    public String borrowApply(BidRequest bidrequest) {
        LoginInfo loginInfo = UserContext.getLoginInfo();
        UserInfo userInfo = iUserInfoService.getCurrentUserInfo(loginInfo.getId());

        if (userInfo.getIsApply()){// 判断是否已发标
            return "borrow_apply_result";
        }else {
            userInfo.addState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS); // 添加用户发标状态码
            iUserInfoService.updateBitState(userInfo); //更新数据库userInfo的状态位
            bidrequest.setCreateUser(loginInfo);
            iBidRequestService.addBidRequest(bidrequest);
            return "borrow_apply_success";
        }
    }

}
