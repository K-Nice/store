package com.horizon.mapper;

import com.horizon.entity.Order;
import com.horizon.entity.OrderItem;

public interface OrderMapper {

    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem item);
}
