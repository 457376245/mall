package com.jh.mall.controller;

import com.jh.mall.service.CategoryService;
import com.jh.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author JH
 * @Date 21/1/11 15:59
 * @Description TODO
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @GetMapping("")
    public ResponseVo getCategorys(){
       return categoryService.getCategorys();
    }

}