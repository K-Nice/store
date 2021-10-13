package com.horizon.service;

import com.horizon.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findHotList();

    Product findById(Integer id);

}
