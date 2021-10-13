package com.horizon.controller;

import com.horizon.entity.Order;
import com.horizon.service.IOrderService;
import com.horizon.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    public IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order data = orderService.create(aid, uid, cids, username);
        return new JsonResult<Order>(OK, data);

    }
}
