package com.horizon.service;

import com.horizon.entity.Order;

public interface IOrderService {

    Order create(Integer aid, Integer pid, Integer[] cids, String username);

}
