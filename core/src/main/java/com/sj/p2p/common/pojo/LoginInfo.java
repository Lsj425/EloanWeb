package com.sj.p2p.common.pojo;

/**
 * 用户登陆信息
 * 用于注册/登陆
 */
public class LoginInfo {
    public static final Byte STATE_NORMAL = 0;
    public static final int USER_MGR = 0;
    public static final int USER_WEB = 1;

    private int userType = 0; // 用户类型（普通用户、管理员）

    private Long id;

    private String username;

    private String password;

    private Byte state;

    private int admin;// 用户类型（普通用户、管理员）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "userType=" + userType +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", admin=" + admin +
                '}';
    }
}