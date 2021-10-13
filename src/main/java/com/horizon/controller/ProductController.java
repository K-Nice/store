package com.horizon.controller;

import com.horizon.entity.Product;
import com.horizon.service.IProductService;
import com.horizon.service.impl.ProductServiceImpl;
import com.horizon.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

    @Autowired
    public IProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> findHotList() {
        List<Product> list = productService.findHotList();
        return new JsonResult<>(OK, list);
    }

    @RequestMapping("{id}/detail")
    public JsonResult<Product> findById(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        return new JsonResult<>(OK, product);
    }
}
