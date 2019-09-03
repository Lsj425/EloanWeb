package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.Account;


public interface AccountMapper {
    /**
     * 新增账户信息
     *
     * @param record
     * @return
     */
    int insert(Account record);

    /**
     * 主键查询
     * @param id
     * @return
     */
    Account selectByPrimaryKey(Long id);

    /**
     * 主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Account record);
}