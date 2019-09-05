package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.MailVerify;

import java.util.List;

public interface MailVerifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MailVerify record);

    MailVerify selectByPrimaryKey(Long id);

    List<MailVerify> selectAll();

    int updateByPrimaryKey(MailVerify record);

    MailVerify selectByUUID(String uuid);
}