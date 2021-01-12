package com.jh.mall.service;

import com.github.pagehelper.PageInfo;
import com.jh.mall.vo.ProductDetailVo;
import com.jh.mall.vo.ProductVo;
import com.jh.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * @Author JH
 * @Date 21/1/12 15:21
 * @Description TODO
 */
public interface ProductService {
    ResponseVo<PageInfo> getProductByCategory(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> getProductById(Integer productId);
}