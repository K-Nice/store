package com.horizon.service;

import com.horizon.vo.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {

    @Autowired
    public ICartService cartService;

    @Test
    public void addToCart() {
        try {
            cartService.addToCart(6, 10000001, "horizon", 2);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findVoByUid() {
        List<CartVo> list = cartService.findVoByUid(6);
        for (CartVo cartVo : list) {
            System.out.println(cartVo);
        }
    }

    @Test
    public void addNum() {
        try {
            cartService.addNum(1,6, "horizon");
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findVoByCids() {
        Integer[] cids = {1, 2, 4};
        Integer uid = 6;
        List<CartVo> list = cartService.findVoByCids(uid, cids);
        System.out.println("count=" + list.size());
        for (CartVo item : list) {
            System.out.println(item);
        }
    }
}
