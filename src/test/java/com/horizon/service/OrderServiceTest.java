package com.horizon.service;

import com.horizon.entity.Order;
import com.horizon.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    public IOrderService orderService;

    @Test
    public void insertOrder() {
        try {
            Integer aid = 6;
            Integer[] cids = {1, 2, 3};
            Integer uid = 6;
            String username = "admin";
            Order order = orderService.create(aid, uid, cids, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
