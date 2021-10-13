package com.horizon.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.horizon.service.ICartService;
import com.horizon.util.JsonResult;
import com.horizon.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_cart")
    public JsonResult<Void> addCart(HttpSession session, Integer pid, Integer amount) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid, pid, username, amount);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVo>> findVoByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<CartVo> list = cartService.findVoByUid(uid);
        return new JsonResult<>(OK, list);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(HttpSession session, @PathVariable("cid") Integer cid) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer num = cartService.addNum(cid, uid, username);
        return new JsonResult<>(OK, num);
    }

    @RequestMapping("list")
    public JsonResult<List<CartVo>> getVoByCids(Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<CartVo> list = cartService.findVoByCids(uid, cids);
        return new JsonResult<>(OK, list);
    }
}
