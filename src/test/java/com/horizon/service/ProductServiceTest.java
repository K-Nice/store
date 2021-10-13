package com.horizon.service;

import com.horizon.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private IProductService productService;

    @Test
    public void findHotList() {
        List<Product> hotList = productService.findHotList();
        for(Product item : hotList) {
            System.out.println(item);
        }
    }

    @Test
    public void findById() {
        System.out.println(productService.findById(10000001));
    }
}
