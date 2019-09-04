package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.LoginInfo;
import org.apache.ibatis.annotations.Param;


public interface LoginInfoMapper {

    /**
     * 根据用户名查询用户数量
     *
     * @param username
     * @return int
     */
    int selectCountByUsername(String username);

    /**
     * 插入登陆资料
     *
     * @param loginInfo
     */
    int insert(LoginInfo loginInfo);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param userType 用户类型
     * @return
     */
    LoginInfo login(@Param("username") String username,
                    @Param("password") String password,
                    @Param("userType") int userType);

}