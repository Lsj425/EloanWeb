package com.sj.p2p.common.mapper;

import com.sj.p2p.common.pojo.SystemDictionaryItem;

import java.util.List;

public interface SystemDictionaryItemMapper {

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    SystemDictionaryItem selectByPrimaryKey(Long id);

    /**
     * 查询前三列
     *
     * @return
     */
    List<SystemDictionaryItem> selectFirstThreeCol();
}