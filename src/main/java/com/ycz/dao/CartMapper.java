package com.ycz.dao;

import com.ycz.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CartMapper {
    void insertCart(@Param("gid") Integer gid,@Param("uid") Integer uid,@Param("gnum") Integer gnum,@Param("total") BigDecimal total);

    Cart selectCartByGid(@Param("gid") Integer gid);

    void updateCart(Cart cart);

    List<Cart> selectCartByUid(@Param("uid") Integer uid);

    void deleteCart(@Param("gid") Integer gid,@Param("uid") Integer uid);
}
