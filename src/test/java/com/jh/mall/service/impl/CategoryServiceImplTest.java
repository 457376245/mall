package com.jh.mall.service.impl;

import com.jh.mall.MallApplicationTests;
import com.jh.mall.dao.CategoryMapper;
import com.jh.mall.pojo.Category;
import com.jh.mall.service.CategoryService;
import com.jh.mall.vo.CategoryVo;
import com.jh.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;
@Slf4j
public class CategoryServiceImplTest extends MallApplicationTests {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void getCategorys() {

    }

    @Test
    public void getChildCategory() {
    }
    @Test
    public void getCategorysById(){
        ResponseVo<List<CategoryVo>> categorysById = categoryService.getCategorysById(100001);
        log.info(categorysById.toString());
    }
}