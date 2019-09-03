package com.sj.p2p.common.service.impl;

import com.sj.p2p.common.mapper.AccountMapper;
import com.sj.p2p.common.pojo.Account;
import com.sj.p2p.common.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getCurrentAccount(Long id) {
        Account account = accountMapper.selectByPrimaryKey(id);
        return account;
    }

    @Override
    public void update(Account account) {
        int res = accountMapper.updateByPrimaryKey(account);
        if (0 == res) {
            throw new RuntimeException("更新失败，乐观锁版本失效，Account：" + account.getId());
        }
    }


}
