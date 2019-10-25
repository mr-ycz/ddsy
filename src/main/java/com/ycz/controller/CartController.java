package com.ycz.controller;

import com.ycz.pojo.Cart;
import com.ycz.service.CartService;
import com.ycz.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/addcart")
    @ResponseBody
    public BigDecimal addcart(Integer gid, Integer uid, Integer gnum, String money){
        BigDecimal totals = new BigDecimal(money);

        Cart cart=cartService.queryCartByGid(gid);
        if (cart==null){
            cartService.addCart(gid,uid,gnum,totals);
        }else {
            cartService.updateCart(cart,gnum,totals);
        }

        BigDecimal newtotals = new BigDecimal(0);
        List<Cart> carts = cartService.queryCartByUid(uid);
        for (Cart cart1 : carts) {
            newtotals=newtotals.add(cart1.getMoney());
        }

        return newtotals;
    }

    @RequestMapping("/getNum")
    @ResponseBody
    public Integer getNum(Integer uid){
        List<Cart> carts = cartService.queryCartByUid(uid);
        return carts.size();
    }

    @RequestMapping("/getcart")
    @ResponseBody
    public String getcart(HttpServletRequest request, Integer uid){
        List<Cart> carts = cartService.queryAllCartByUid(uid);
        request.getSession().setAttribute("carts", carts);
        BigDecimal cartfee=new BigDecimal(0);
        for (Cart cart : carts) {
            cartfee = cartfee.add(cart.getMoney());
        }
        request.getSession().setAttribute("cartfee", cartfee);
        return ""+carts.size();
    }

    @RequestMapping("/tocart")
    public String tocart(HttpServletRequest request, Integer uid){
        getcart(request, uid);
        return "cart";
    }

    @RequestMapping("/delcart")
    public String delcart(Integer gid, Integer uid, HttpServletRequest request){
        System.out.println("gid:"+gid+",uid:"+uid);
        cartService.deleteCart(gid,uid);

        getcart(request, uid);
        return "cart";
    }

    @RequestMapping("/opcart")
    @ResponseBody
    public BigDecimal opcart(Integer gid, Integer uid, String op, String allfee, Integer number, HttpServletRequest request){
        //op为1是选中,op为2是取消
        List<Cart> carts = cartService.queryCartByUid(uid);
        Cart cart = cartService.queryCartByGid(gid);
        BigDecimal money=new BigDecimal(allfee);

        if (op.equals("1")){
            money=money.add(cart.getMoney());
            number+=1;
        }else {
            money=money.add(cart.getMoney().multiply(new BigDecimal(-1)));
            number-=1;
        }

        request.getSession().setAttribute("allGoodsNum", number);

        return money;
    }
}
