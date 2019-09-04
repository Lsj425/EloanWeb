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
}
