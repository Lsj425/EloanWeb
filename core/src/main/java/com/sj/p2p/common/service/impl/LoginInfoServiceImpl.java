package com.sj.p2p.common.service.impl;

import com.sj.p2p.common.mapper.AccountMapper;
import com.sj.p2p.common.mapper.IpLogMapper;
import com.sj.p2p.common.mapper.LoginInfoMapper;
import com.sj.p2p.common.mapper.UserInfoMapper;
import com.sj.p2p.common.pojo.*;
import com.sj.p2p.common.query.LoginInfoQueryObject;
import com.sj.p2p.common.query.PageResultSet;
import com.sj.p2p.common.service.ILoginInfoService;
import com.sj.p2p.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class LoginInfoServiceImpl implements ILoginInfoService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private IpLogMapper ipLogMapper;


    /**
     * 检查用户名是否已存在
     *
     * @param username
     * @return 返回用户个数
     */
    @Override
    public int checkUsername(String username) {
        int count = loginInfoMapper.selectCountByUsername(username);
        return count;
    }

    /**
     * 新用户注册
     *
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) {
        /*
         * 逻辑思路
         * 1. 判断用户名是否存在
         * 2. 如果不存在,设值并保存这个对象
         * 3. 如果存在,直接抛错
         *
         */
        int count = checkUsername(username);

        if (count <= 0) {
            // 初始化登陆信息LoginInfo
            LoginInfo li = new LoginInfo();
            li.setUsername(username);
            li.setPassword(password);
            li.setUserType(1);
            li.setAdmin(0);
            li.setState(LoginInfo.STATE_NORMAL);
            loginInfoMapper.insert(li);

            // 初始化账户信息Account
            Long id = li.getId();
            Account account = new Account();
            account.setId(id);
            accountMapper.insert(account);

            // 初始化个人资料UserInfo
            UserInfo userInfo = new UserInfo();
            userInfo.setId(id);
            userInfoMapper.insert(userInfo);
        } else {
            // 如果存在,直接抛错
            throw new RuntimeException("用户名已存在!");
        }

    }

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     */
    @Override
    public LoginInfo login(String username, String password, HttpServletRequest request, int userType) {

        LoginInfo loginInfo = loginInfoMapper.login(username, password, userType);

        // 添加登陆日志
        IpLog ipLog = new IpLog();
        ipLog.setIp(request.getRemoteAddr());
        ipLog.setUsername(username);
        ipLog.setLoginTime(new Date());

        if (loginInfo != null) {
            // 将登录用户的数据，通过UserContext工具类，存放至session
            UserContext.putLoginInfo(loginInfo);
            ipLog.setState(IpLog.LOGIN_SUCCESS);
        } else {
            ipLog.setState(IpLog.LOGIN_FAILED);
        }
        ipLogMapper.insert(ipLog);
        return loginInfo;

    }

    /**
     * 用户信息分页查询
     *
     * @param loginInfoQueryObject
     * @return
     */
    @Override
    public PageResultSet queryForPage(LoginInfoQueryObject loginInfoQueryObject) {

        int count = loginInfoMapper.queryForCount();

        PageResultSet pageResultSet;
        //如果存在符合条件的数据，对数据进行分页查询，获取当前页的数据;没有则返回空的数据集
        if (count > 0) {
            List<LoginInfo> list = loginInfoMapper.queryForPage(loginInfoQueryObject);
            pageResultSet = new PageResultSet(
                    list,
                    count,
                    loginInfoQueryObject.getCurrentPage(),
                    loginInfoQueryObject.getPageSize());
        } else {
            pageResultSet = PageResultSet.empty(loginInfoQueryObject.getPageSize());
        }

        return pageResultSet;
    }
}
