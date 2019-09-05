package com.sj.p2p.common.service;

public interface IVerifyCodeService {

    /**
     * 发送验证码
     *
     * @param phoneNumber
     */
    void sendVerifyCode(String phoneNumber);

    /**
     * 验证信息
     *
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    boolean validate(String phoneNumber, String verifyCode);
}
