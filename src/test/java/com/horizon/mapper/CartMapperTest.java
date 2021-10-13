package com.horizon.mapper;

import com.horizon.entity.Cart;
import com.horizon.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {

    @Autowired
    public CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(6);
        cart.setPid(10000001);
        cart.setNum(3);
        cart.setPrice(4L);
        Integer rows = cartMapper.insert(cart);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 2, "214", new Date());
    }

    @Test
    public void findByUidAndPid() {
        System.out.println(cartMapper.findByUidAndPid(6, 10000001));
    }

    @Test
    public void findVoByUid() {
        List<CartVo> list = cartMapper.findVoByUid(6);
        for (CartVo cartVo : list) {
            System.out.println(cartVo);
        }
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(2));
    }

    @Test
    public void findVoByCids() {
        Integer[] cids = {1, 3, 5};
        List<CartVo> list = cartMapper.findVoByCids(cids);
        for (CartVo cartVo : list) {
            System.out.println(cartVo);
        }
    }
}
