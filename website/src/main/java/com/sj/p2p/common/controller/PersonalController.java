package com.sj.p2p.common.controller;

import com.sj.p2p.common.pojo.Account;
import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.service.IAccountService;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonalController {

    @Autowired
    private IAccountService iAccountService;

    // 用户登陆后的个人中心主页
    @RequestMapping("personal")
    public String personalCenter(Model model) {

        /**
         * 查询并显示用户信息
         * loginInfo表:用户名（登陆时已存入session）
         * userInfo表：真是姓名（待完成）、邮箱、电话认证信息
         */
        LoginInfo loginInfo = UserContext.getLoginInfo();
        Account currentAccount = iAccountService.getCurrentAccount(loginInfo.getId());
//        UserInfo currentUserInfo= iUserInfoService.getCurrentAccount(loginInfo.getId());
        model.addAttribute("account", currentAccount);
//        model.addAttribute("userinfo", currentUserInfo);

        // 计算账户资产信息并显示
        List<String> list=new ArrayList<>();
        list.add(currentAccount.getUsableAmount().toString());
        list.add(currentAccount.getFrozenAmount().toString());
        list.add(currentAccount.getUnreceivedInterest().toString());
        list.add(currentAccount.getUnreceivedPrincipal().toString());
        list.add(currentAccount.getUnpaidAmount().toString());
        BigDecimal count=new BigDecimal(0);
        for(String s:list) {
            count = count.add(new BigDecimal(s));
        }
        model.addAttribute("count", count);
        return "personal";
    }

}
