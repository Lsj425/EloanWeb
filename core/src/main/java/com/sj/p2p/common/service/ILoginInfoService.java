package com.sj.p2p.common.service;

import com.sj.p2p.common.pojo.LoginInfo;

import javax.servlet.http.HttpServletRequest;

public interface ILoginInfoService {
    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    int checkUsername(String username);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    void register(String username, String password);

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @param request
     * @param userType
     * @return
     */
    LoginInfo login(String username, String password, HttpServletRequest request, int userType);
}
