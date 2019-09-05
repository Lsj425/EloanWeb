package com.sj.p2p.common.service.impl;

import com.sj.p2p.common.mapper.MailVerifyMapper;
import com.sj.p2p.common.mapper.SystemDictionaryItemMapper;
import com.sj.p2p.common.mapper.UserInfoMapper;
import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.pojo.MailVerify;
import com.sj.p2p.common.pojo.SystemDictionaryItem;
import com.sj.p2p.common.pojo.UserInfo;
import com.sj.p2p.common.service.IMailVerifyService;
import com.sj.p2p.common.service.IUserInfoService;
import com.sj.p2p.common.service.IVerifyCodeService;
import com.sj.p2p.common.util.BitStatesUtils;
import com.sj.p2p.common.util.DateUtil;
import com.sj.p2p.common.util.SysConstant;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private SystemDictionaryItemMapper sysDicItemMapper;

    @Autowired
    private IVerifyCodeService iVerifyCodeService;

    @Autowired
    private MailVerifyMapper mailVerifyMapper;

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

    @Override
    public void updateBitState(UserInfo userInfo){
        // 更新个人资料
        userInfoMapper.updateByPrimaryKey(userInfo);
    }


    @Override
    public void bindPhone(String phoneNumber, String verifyCode) {
        // 先做验证码的校验 (一般关于验证码的都交给验证码相关服务)
        if (iVerifyCodeService.validate(phoneNumber,verifyCode)) {
            //如果校验成功,给当前用户绑定手机号和状态码
            UserInfo userInfo = getCurrentUserInfo(UserContext.getLoginInfo().getId());
            //先判断当前用户是否已经绑定了手机
            if ( !userInfo.getIsBindPhone()) { //表示当前没有绑定手机
                System.err.println("用户未绑定手机判断成功");
                //给当前用户添加状态码和手机号
                userInfo.setPhoneNumber(phoneNumber);
                userInfo.addState(BitStatesUtils.OP_BIND_PHONE);
                userInfoMapper.updateByPrimaryKey(userInfo);
            }
        }else{
            throw new RuntimeException("绑定失败");
        }
    }

    /**
     * 绑定邮箱的实现
     */
    public void bindEmail(String uuid) {
        //根据uuid查村mailVerify对象
        MailVerify mailVerify = mailVerifyMapper.selectByUUID(uuid);
        if (null != mailVerify
                && DateUtil.getBetweenSecond(mailVerify.getSendTime(), new Date()) < SysConstant.EMAIL_VALID_DAY * 24 * 3600 ) {
            //如果有 且在有效期内 的用户没有绑定邮箱
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(mailVerify.getLoginInfoId());
            if ( !userInfo.getIsBindEmail()) {
                //添加邮箱状态码  设置email属性
                userInfo.addState(BitStatesUtils.OP_BIND_EMAIL);
                userInfo.setEmail(mailVerify.getEmail());
                userInfoMapper.updateByPrimaryKey(userInfo);
            }
        }else{
            throw new RuntimeException("验证邮箱地址错误!");
        }
    }
}
