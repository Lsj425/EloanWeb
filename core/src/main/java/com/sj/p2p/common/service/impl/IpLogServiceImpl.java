package com.sj.p2p.common.service.impl;

import com.sj.p2p.common.mapper.IpLogMapper;
import com.sj.p2p.common.pojo.IpLog;
import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.query.IpLogQueryObject;
import com.sj.p2p.common.query.PageResultSet;
import com.sj.p2p.common.service.IIpLogService;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpLogServiceImpl implements IIpLogService {
    @Autowired
    private IpLogMapper iplogMapper;

    /**
     * 用户登陆历史记录分页查询
     *
     * @param iplogQueryObject
     * @return
     */
    @Override
    public PageResultSet queryForUserIpLogPage(IpLogQueryObject iplogQueryObject) {
        // 获取session中当前用户的username
        LoginInfo loginInfo = UserContext.getLoginInfo();
        int count = iplogMapper.queryForUserIpLogCount(loginInfo.getUsername(),iplogQueryObject);

        PageResultSet pageResultSet;
        //如果存在符合条件的数据，对数据进行分页查询，获取当前页的数据;没有则返回空的数据集
        if (count > 0) {
            List<IpLog> list = iplogMapper.queryForUserIpPage(loginInfo.getUsername(),iplogQueryObject);
            pageResultSet = new PageResultSet(
                    list,
                    count,
                    iplogQueryObject.getCurrentPage(),
                    iplogQueryObject.getPageSize());
        } else {
            pageResultSet = PageResultSet.empty(iplogQueryObject.getPageSize());
        }

        return pageResultSet;
    }
}
