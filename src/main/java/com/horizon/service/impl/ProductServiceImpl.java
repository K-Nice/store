package com.horizon.service.impl;

import com.horizon.entity.Product;
import com.horizon.mapper.ProductMapper;
import com.horizon.service.IProductService;
import com.horizon.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        return productMapper.findHotList();
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);

        if(product == null) {
            throw new ProductNotFoundException("商品不存在");
        }

        return product;
    }

}
