package com.jh.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jh.mall.dao.ProductMapper;
import com.jh.mall.enums.ResponseEnum;
import com.jh.mall.pojo.Product;
import com.jh.mall.service.CategoryService;
import com.jh.mall.service.ProductService;
import com.jh.mall.vo.CategoryVo;
import com.jh.mall.vo.ProductDetailVo;
import com.jh.mall.vo.ProductVo;
import com.jh.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author JH
 * @Date 21/1/12 15:25
 * @Description TODO
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private CategoryService categoryService;

    @Override
    public ResponseVo<PageInfo> getProductByCategory(Integer categoryId, Integer pageNum, Integer pageSize) {
        //查出category和子类
        List<CategoryVo> categorys = categoryService.getCategorysById(categoryId).getData();
        //用于存放id
        Set<Integer> categoryIds = new HashSet<>();

        categorys.stream().forEach(categoryVo -> {
            //添加一级目录id
            categoryIds.add(categoryVo.getId());
            //添加一级目录子类id
            categoryVo.getChildCategory().stream().forEach(child -> {
                categoryIds.add(child.getId());
            });
        });
        //查出商品列表并分页
        PageHelper.startPage(pageNum,pageSize);
        List<Product> productList = productMapper.getProductByCategory(categoryIds);
        //转换为vo列表
        List<ProductVo> productVos = productList.stream().map(product -> {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(product, productVo);
            return productVo;
        }).collect(Collectors.toList());
        PageInfo<ProductVo> pageInfo = new PageInfo<>(productVos);
        pageInfo.setList(productVos);
        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> getProductById(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product.getStatus().equals(2)||product.getStatus().equals(3)){
            return ResponseVo.error(ResponseEnum.PRODUCT_ERROR);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product,productDetailVo);
        return ResponseVo.success(productDetailVo);
    }
}