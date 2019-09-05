package com.sj.p2p.common.service;

public interface IMailVerifyService {
    /**
     * 发送邮件
     *
     * @param email
     */
    void sendVerifyEmail(String email);
}
