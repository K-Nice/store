package com.horizon.service.impl;

import com.horizon.entity.Cart;
import com.horizon.entity.Product;
import com.horizon.mapper.CartMapper;
import com.horizon.mapper.ProductMapper;
import com.horizon.service.ICartService;
import com.horizon.service.ex.*;
import com.horizon.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, String username, Integer amount) {

        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();

        if(cart == null) {
            Product product = productMapper.findById(pid);
            if(product == null) {
                throw new ProductNotFoundException("商品不存在");
            }

            cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            cart.setPrice(product.getPrice());
            cart.setCreatedUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);

            int rows = cartMapper.insert(cart);
            if(rows != 1) {
                throw new InsertException("商品加入购物车时产生异常[1]");
            }
        } else {
            int cid = cart.getCid();
            int num = amount + cart.getNum();

            int rows = cartMapper.updateNumByCid(cid, num, username, date);
            if(rows != 1) {
                throw new UpdateException("商品加入购物车时产生异常[2]");
            }
        }
    }

    @Override
    public List<CartVo> findVoByUid(Integer uid) {
        return cartMapper.findVoByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart cart = cartMapper.findByCid(cid);

        if(cart == null) {
            throw new CartNotFoundException("购物车数据不存在");
        } else if(!cart.getUid().equals(uid)) {
            throw new AccessDeniedException("非法操作");
        }

        Integer num = cart.getNum() + 1;
        Date date = new Date();

        int rows = cartMapper.updateNumByCid(cid, num, username, date);
        if(rows != 1) {
            throw new UpdateException("修改商品数量时产生未知异常");
        }

        return num;
    }

    @Override
    public List<CartVo> findVoByCids(Integer uid, Integer[] cids) {
        List<CartVo> list = cartMapper.findVoByCids(cids);

        Iterator<CartVo> it = list.iterator();
        while(it.hasNext()) {
            CartVo vo = it.next();
            if(!vo.getUid().equals(uid)) {
                it.remove();
            }
        }

        return list;
    }
}
