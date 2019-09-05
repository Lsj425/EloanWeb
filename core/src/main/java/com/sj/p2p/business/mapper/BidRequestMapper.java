package com.sj.p2p.business.mapper;

import com.sj.p2p.business.pojo.BidRequest;
import com.sj.p2p.common.query.BidRequestQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BidRequestMapper {

    /**
     * 插入一条申请数据
     *
     * @param record
     * @return
     */
    int insert(BidRequest record);

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    BidRequest selectByPrimaryKey(Long id);

    /**
     * 根据绑定状态查询
     *
     * @return
     */
    List<BidRequest> selectAllByState();

    /**
     * 查询所有投标记录的数量
     *
     * @return
     */
    int queryForCount();

    /**
     * 查询当前页数据（分页查询）
     * 标题、借款人、申请时间、借款金额、期限、利率、总利息、状态
     *
     * @return
     */
    List<BidRequest> queryForPage(BidRequestQueryObject bidRequestQueryObject);

    /**
     * 查询所有投标记录的数量
     *
     * @return
     */
    int queryForCountByState(@Param("bidRequestQueryObject") BidRequestQueryObject bidRequestQueryObject);

    /**
     * 根据bidRequestState查询当前页数据（分页查询）
     * 借款人、标题、利率、借款金额、还款方式
     *
     * @return
     */
    List<BidRequest> queryForPageByState(@Param("bidRequestQueryObject") BidRequestQueryObject bidRequestQueryObject);

    /**
     * 主键更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(BidRequest record);
}