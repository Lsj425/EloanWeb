package com.sj.p2p.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {
    // 控制管理员登陆后的主页跳转
    @RequestMapping("index")
    public String index() {

        return "main";
    }
}
