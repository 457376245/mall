package com.jh.mall.service.impl;

import com.jh.mall.MallApplicationTests;
import com.jh.mall.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends MallApplicationTests {
    @Autowired
    private ProductService productService;

    @Test
    public void getProductByCategory() {
        productService.getProductByCategory(null,2,2);
    }
}