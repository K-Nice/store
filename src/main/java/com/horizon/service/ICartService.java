package com.horizon.service;

import com.horizon.vo.CartVo;

import java.util.List;

public interface ICartService {

    void addToCart(Integer uid, Integer pid, String username, Integer amount);

    List<CartVo> findVoByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);

    List<CartVo> findVoByCids(Integer uid, Integer[] cids);
}
