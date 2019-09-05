package com.sj.p2p.common.service;

import com.sj.p2p.common.query.IpLogQueryObject;
import com.sj.p2p.common.query.PageResultSet;

public interface IIpLogService {

    /**
     * 用户登陆历史分页查询
     *
     * @param iplogQueryObject
     * @return
     */
    PageResultSet queryForUserIpLogPage(IpLogQueryObject iplogQueryObject);

}
