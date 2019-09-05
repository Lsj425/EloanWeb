package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.LoginInfo;
import com.sj.p2p.common.query.LoginInfoQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface LoginInfoMapper {

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    LoginInfo selectByPrimaryKey(Long id);

    /**
     * 根据用户名查询用户数量
     *
     * @param username
     * @return int
     */
    int selectCountByUsername(String username);

    /**
     * 插入登陆资料
     *
     * @param loginInfo
     */
    int insert(LoginInfo loginInfo);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @param userType 用户类型
     * @return
     */
    LoginInfo login(@Param("username") String username,
                    @Param("password") String password,
                    @Param("userType") int userType);

    /**
     * 查询所有用户的数量
     *
     * @return
     */
    int queryForCount();

    /**
     * 查询当前页数据（分页查询）
     * 用户名、登陆状态、管理员/用户标志位
     *
     * @param loginInfoQueryObject
     * @return
     */
    List<LoginInfo> queryForPage(LoginInfoQueryObject loginInfoQueryObject);
}