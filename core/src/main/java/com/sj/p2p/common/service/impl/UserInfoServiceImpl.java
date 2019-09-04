package com.sj.p2p.common.service.impl;

import com.sj.p2p.common.mapper.SystemDictionaryItemMapper;
import com.sj.p2p.common.mapper.UserInfoMapper;
import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.pojo.SystemDictionaryItem;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.service.IUserInfoService;
import com.sj.p2p.common.util.BitStatesUtils;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SystemDictionaryItemMapper sysDicItemMapper;

    @Override
    public UserInfo getCurrentUserInfo(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> getSysDicItem() {
        return sysDicItemMapper.selectFirstThreeCol();
    }

    @Override
    public void updateBasicInfo(UserInfo userInfoVo) {
        try {
            // 获取需要保存userInfo对象（数据库原始对象）
            LoginInfo loginInfo = UserContext.getLoginInfo();
            UserInfo userInfo = getCurrentUserInfo(loginInfo.getId());

            //将页面提交的数据 设置到原有userInfo对象中
            userInfo.setEducationBackgroundItem(userInfoVo.getEducationBackgroundItem());
            userInfo.setKidCountItem(userInfoVo.getKidCountItem());
            userInfo.setIncomeGradeItem(userInfoVo.getIncomeGradeItem());
            userInfo.setHouseConditionItem(userInfoVo.getHouseConditionItem());
            userInfo.setMarriageItem(userInfoVo.getMarriageItem());

            // 设置状态码
            if ( !userInfo.getIsBasicInfo()) {
                userInfo.addState(BitStatesUtils.OP_USER_INFO);
            }

            // 更新个人资料
            userInfoMapper.updateByPrimaryKey(userInfo);
        } catch (Exception e) {
            throw new RuntimeException("个人资料保存出错"+e.getMessage());

        }
    }
}
