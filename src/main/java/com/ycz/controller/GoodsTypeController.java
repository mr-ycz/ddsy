package com.ycz.controller;

import com.ycz.pojo.GoodsType;
import com.ycz.service.GoodsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/goodstype")
public class GoodsTypeController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/getGoodstype")
    public String getGoodstype(HttpServletRequest request){
        List<GoodsType> goodsTypes=goodsTypeService.selectAllGoodsType();
        request.getSession().setAttribute("goodsTypes1", goodsTypes);
        return "goodstype";
    }

    @RequestMapping("/getGoodstypeById")
    public String getGoodstypeById(Integer id, HttpServletRequest request){
        GoodsType goodsTypes=goodsTypeService.selectGoodsTypeById(id);
        request.getSession().setAttribute("goodsTypes2", goodsTypes);

        return "goods";
    }

    @RequestMapping("/getGoodstypeById2")
    public String getGoodstypeById2(Integer id, HttpServletRequest request, String open, Integer pageNum, Integer pageSize){
        if (pageNum==null||pageNum<0){
            pageNum=1;
        }

        request.setAttribute("open", open);

        GoodsType goodsTypes=goodsTypeService.selectAllGoodsTypeById(id,open,pageNum,10,request);

        request.getSession().setAttribute("goodsTypes3", goodsTypes);

        return "list";
    }

}
