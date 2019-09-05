package com.sj.p2p.common.service;

import com.sj.p2p.common.pojo.SystemDictionaryItem;
import com.sj.p2p.common.pojo.UserInfo;

import java.util.List;

public interface IUserInfoService {

    /**
     * 获取当前用户资料对象
     *
     * @return id
     */
    UserInfo getCurrentUserInfo(Long id);

    /**
     * 获取下拉框内容
     *
     * @return
     */
    List<SystemDictionaryItem> getSysDicItem();

    /**
     * 用户基本资料的保存
     *
     * @param userInfo
     */
    void updateBasicInfo(UserInfo userInfo);

    /**
     * 更新用户绑定状态
     *
     * @param userInfo
     */
    void updateBitState(UserInfo userInfo);

    /**
     * 用户绑定手机
     *
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     * 绑定邮箱
     *
     * @param uuid
     */
    void bindEmail(String uuid);
}
