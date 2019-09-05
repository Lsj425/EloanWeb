package com.sj.p2p.common.controller;

import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.business.service.IBidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserMainController {

    @Autowired
    private IBidRequestService iBidRequestService;

    @RequestMapping("index")
    public String index(Model model) {

        List<BidRequest> list=iBidRequestService.selectByState();
        model.addAttribute("bidRequests",list);
        return "main";
    }
}
