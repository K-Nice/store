package com.horizon.mapper;

import com.horizon.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> list = productMapper.findHotList();
        for(Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findById() {
        System.out.println(productMapper.findById(10000001));
    }

}
