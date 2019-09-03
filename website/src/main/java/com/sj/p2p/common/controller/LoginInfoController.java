package com.sj.p2p.common.controller;

import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.service.ILoginInfoService;
import com.sj.p2p.common.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginInfoController {
    @Autowired
    private ILoginInfoService iLoginInfoService;

    // 注册时检查用户名是否存在，确保注册时数据库表里没有同名用户
    @RequestMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username) {
        int count  = iLoginInfoService.checkUsername(username);
        return count <= 0;
    }

    // 用户注册
    @RequestMapping("register")
    @ResponseBody
    public JSONResult register(String username, String password) {
        JSONResult json = new JSONResult();
        try {
            iLoginInfoService.register(username, password);
            json.setSuccess(true);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }

    // 用户登陆
    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request) {
        LoginInfo loginInfo = iLoginInfoService.login(username, password, request, LoginInfo.USER_WEB);
        JSONResult json = new JSONResult();
        if(null == loginInfo){
            json.setSuccess(false);
            json.setMsg("登录失败,用户名或密码无效");
        }else {
            json.setSuccess(true);
        }
        return json;
    }

}
