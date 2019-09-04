package com.sj.p2p.common.pojo;

import java.util.Date;

public class IpLog {
    public static final Byte LOGIN_SUCCESS = 1; // 定义登录成功状态

    public static final Byte LOGIN_FAILED = 0; // 定义登录失败状态

    private Long id;

    private String ip; // 登录IP

    private Byte state; // 登录状态（成功、失败）

    private String username; // 用户名

    private Long loginInfoId; // 登录用户对应的id

    private Byte userType; // 用户类型

    private Date loginTime; // 登录时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}