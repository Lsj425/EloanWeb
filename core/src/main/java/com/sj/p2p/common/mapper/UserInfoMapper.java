package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.UserInfo;

public interface UserInfoMapper {
    /**
     * 插入个人资料
     *
     * @param record
     */
    int insert(UserInfo record);

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    UserInfo selectByPrimaryKey(Long id);

    /**
     * 更新个人资料
     *
     * @param userInfo
     * @return
     */
    int updateByPrimaryKey(UserInfo userInfo);
}