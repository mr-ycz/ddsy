package com.ycz.dao;

import com.ycz.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsTypeMapper {
    List<GoodsType> selectAllGoodsType();

    GoodsType selectGoodsTypeById(@Param("id") Integer id);

    GoodsType selectAllGoodsTypeById(@Param("id") Integer id, @Param("on") String on);
}
