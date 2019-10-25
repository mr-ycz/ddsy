package com.ycz.service;

import com.ycz.pojo.GoodsType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> selectAllGoodsType();

    GoodsType selectGoodsTypeById(Integer id);

    GoodsType selectAllGoodsTypeById(Integer id, String on, Integer pageNum, Integer pageSize, HttpServletRequest request);
}
