package com.jh.mall.service;

import com.jh.mall.pojo.Category;
import com.jh.mall.vo.CategoryVo;
import com.jh.mall.vo.ResponseVo;

import java.util.List;

public interface CategoryService {
    ResponseVo<List<CategoryVo>> getCategorys();
}
