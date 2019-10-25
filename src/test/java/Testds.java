import com.ycz.dao.GoodsMapper;
import com.ycz.pojo.Goods;
import com.ycz.pojo.User;
import com.ycz.service.GoodsTypeService;
import com.ycz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Testds {

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void test2(){
        List<User> users = userService.selectAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void test4(){
        for (int i = 10; i < 50; i++) {
            Goods goods=new Goods(null, "榴莲"+i, new Date(), "goods.jpg", new BigDecimal(99+i), "原自泰国猫山王", 1, "banner01.jpg");
            goodsMapper.insertGoods(goods);
        }
    }

}
