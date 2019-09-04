package com.sj.p2p.common.controller;

import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.pojo.SystemDictionaryItem;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.service.IUserInfoService;
import com.sj.p2p.common.util.JSONResult;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UserInfoController {
    @Autowired
    private IUserInfoService iUserInfoService;

    // 查询出个人中心下拉框的值并显示
    @RequestMapping("userInfo")
    public String userInfo(Model model) {

        // 初始化前三项基本信息
        LoginInfo loginInfo = UserContext.getLoginInfo();
        UserInfo userInfo = iUserInfoService.getCurrentUserInfo(loginInfo.getId());
        model.addAttribute("userInfo", userInfo);

        // 初始化具体信息
        List<SystemDictionaryItem> incomeGrade = new ArrayList<>();
        List<SystemDictionaryItem> educationBackground = new ArrayList<>();
        List<SystemDictionaryItem> marriage = new ArrayList<>();
        List<SystemDictionaryItem> kidCount = new ArrayList<>();
        List<SystemDictionaryItem> houseCondition = new ArrayList<>();
        List<SystemDictionaryItem> sysDicItemList = iUserInfoService.getSysDicItem();
        for(SystemDictionaryItem s:sysDicItemList) {
            if(s.getParentId()==1L){
                incomeGrade.add(s);
            }else if(s.getParentId()==2L){
                educationBackground.add(s);
            }else if(s.getParentId()==3L){
                marriage.add(s);
            }else if(s.getParentId()==4L){
                kidCount.add(s);
            }else if(s.getParentId()==5L){
                houseCondition.add(s);
            }
        }

        model.addAttribute("educationBackgrounds",educationBackground);
        model.addAttribute("incomeGrades",incomeGrade);
        model.addAttribute("marriages",marriage);
        model.addAttribute("kidCounts",kidCount);
        model.addAttribute("houseConditions",houseCondition);
        return "userInfo";
    }

    // 保存个人资料
    @RequestMapping("userInfo_save")
    @ResponseBody
    public JSONResult userInfoSave(UserInfo userInfo) {

        JSONResult json = new JSONResult();
        try {
            iUserInfoService.updateBasicInfo(userInfo);
            json.setSuccess(true);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }

}
