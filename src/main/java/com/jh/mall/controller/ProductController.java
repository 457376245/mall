package com.jh.mall.controller;

import com.github.pagehelper.PageInfo;
import com.jh.mall.service.ProductService;
import com.jh.mall.vo.ProductDetailVo;
import com.jh.mall.vo.ProductVo;
import com.jh.mall.vo.ResponseVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author JH
 * @Date 21/1/12 16:28
 * @Description TODO
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping("")
    public ResponseVo<PageInfo> getProducts(@RequestParam(required = false) Integer categoryId,
                                            @RequestParam(required = false,defaultValue = "1") Integer pageSize,
                                            @RequestParam(required = false,defaultValue = "10") Integer pageNum ){
        return productService.getProductByCategory(categoryId,pageSize,pageNum);

    }
    @GetMapping("/{productId}")
    public ResponseVo<ProductDetailVo> getProductById(@PathVariable("productId") Integer productId){
        return productService.getProductById(productId);
    }
}