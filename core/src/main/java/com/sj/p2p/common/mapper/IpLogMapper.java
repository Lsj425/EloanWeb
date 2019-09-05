package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.IpLog;
import com.sj.p2p.common.query.IpLogQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IpLogMapper {

    /**
     * 插入一条数据
     *
     * @param record
     * @return
     */
    int insert(IpLog record);

    /**
     * 查询当前用户所有登陆记录的数量
     *
     * @return
     */
    int queryForUserIpLogCount(@Param("username")String username, @Param("ipLogQueryObject") IpLogQueryObject iplogQueryObject);


    /**
     * 查询当前页数据（分页查询）
     * 用户名、登陆时间、登陆IP、登陆状态
     *
     * @return
     */
    List<IpLog> queryForUserIpPage(@Param("username")String username, @Param("ipLogQueryObject")IpLogQueryObject iplogQueryObject);
}