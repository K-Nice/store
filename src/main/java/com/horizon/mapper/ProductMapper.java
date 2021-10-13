package com.horizon.mapper;

import com.horizon.entity.Product;

import java.util.List;

public interface ProductMapper {

    List<Product> findHotList();

    Product findById(Integer id);

}
