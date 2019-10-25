package com.ycz.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ycz.dao.GoodsMapper;
import com.ycz.dao.GoodsTypeMapper;
import com.ycz.pojo.Goods;
import com.ycz.pojo.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsType> selectAllGoodsType() {
        return goodsTypeMapper.selectAllGoodsType();
    }

    @Override
    public GoodsType selectGoodsTypeById(Integer id) {
        GoodsType goodsType = goodsTypeMapper.selectGoodsTypeById(id);

        /*PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods=goodsMapper.selectByTypeid(id);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);*/

//        request.getSession().setAttribute("goodsPageInfo", goodsPageInfo);
        return goodsType;
    }

    @Override
    public GoodsType selectAllGoodsTypeById(Integer id, String on, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        GoodsType goodsType = goodsTypeMapper.selectAllGoodsTypeById(id,on);

        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods=goodsMapper.selectByTypeid(id,on);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);

        request.getSession().setAttribute("goodsPageInfo", goodsPageInfo);
        return goodsType;
    }
}
