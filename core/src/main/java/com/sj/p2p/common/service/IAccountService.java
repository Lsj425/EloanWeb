package com.sj.p2p.common.service;

import com.sj.p2p.common.pojo.Account;

public interface IAccountService {

    /**
     * 得到当前登陆用户对应的Account对象
     *
     * @return
     */
    Account getCurrentAccount(Long id);

    /**
     * 更新账户信息需要乐观锁支持
     *
     * @param account
     */
    void update(Account account);
}
