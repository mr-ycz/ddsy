package com.ycz.service;

import com.ycz.dao.CartMapper;
import com.ycz.dao.GoodsMapper;
import com.ycz.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addCart(Integer gid, Integer uid, Integer gnum, BigDecimal total) {
        cartMapper.insertCart(gid,uid,gnum,total);
    }

    @Override
    public Cart queryCartByGid(Integer gid) {
        return cartMapper.selectCartByGid(gid);
    }

    @Override
    public void updateCart(Cart cart, Integer gnum,BigDecimal totals) {
        cart.setGnum(cart.getGnum()+gnum);
        cart.setMoney(cart.getMoney().add(totals.multiply(new BigDecimal(gnum))));
        cartMapper.updateCart(cart);
    }

    @Override
    public List<Cart> queryCartByUid(Integer uid) {
        List<Cart> carts = cartMapper.selectCartByUid(uid);
        if (carts!=null){
            for (Cart cart : carts) {
                cart.setGoods(goodsMapper.selectById(cart.getGid()));
            }
        }
        return carts;
    }

    @Override
    public List<Cart> queryAllCartByUid(Integer uid) {
        List<Cart> carts = cartMapper.selectCartByUid(uid);
        if (carts!=null){
            for (Cart cart : carts) {
                cart.setGoods(goodsMapper.selectById(cart.getGid()));
            }
        }
        return carts;
    }

    @Override
    public void deleteCart(Integer gid, Integer uid) {
        cartMapper.deleteCart(gid,uid);
    }
}
