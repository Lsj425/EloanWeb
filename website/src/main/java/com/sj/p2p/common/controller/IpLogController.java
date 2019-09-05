package com.sj.p2p.common.controller;

import com.sj.p2p.common.query.IpLogQueryObject;
import com.sj.p2p.common.service.IIpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IpLogController {
    @Autowired
    private IIpLogService iIpLogService;

    @RequestMapping("ipLog")
    public String ipLog(IpLogQueryObject ipLogQueryObject, Model model) {

        model.addAttribute("pageResultSet",iIpLogService.queryForUserIpLogPage(ipLogQueryObject));
        IpLogQueryObject ipLogQueryObject1=new IpLogQueryObject();
        ipLogQueryObject1.setBeginDate(ipLogQueryObject.getBeginDate());
        ipLogQueryObject1.setEndDate(ipLogQueryObject.getEndDate());
        ipLogQueryObject1.setState(ipLogQueryObject.getState());

        model.addAttribute("ipLogQueryObject",ipLogQueryObject1);
        return "iplog_list";
    }
}
