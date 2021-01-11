package com.jh.mall.service.impl;

import com.jh.mall.dao.CategoryMapper;
import com.jh.mall.pojo.Category;
import com.jh.mall.service.CategoryService;
import com.jh.mall.vo.CategoryVo;
import com.jh.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author JH
 * @Date 21/1/11 15:18
 * @Description TODO
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseVo<List<CategoryVo>> getCategorys() {
        //获得所有category数据
        List<Category> allCategorys = categoryMapper.getCategorys();
        //将categoryList转为categoryVoList
        List<CategoryVo> categoryVos = allCategorys.stream().map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
        //通过stream获得树形categoryList
        //得到1级菜单
        List<CategoryVo> categoryList = categoryVos.stream().filter(category -> category.getParentId() == 0).
                map(category -> {
                    //遍历装填子菜单
                    category.setChildCategory(getChildCategory(categoryVos, category));
                    return category;
        }).sorted(Comparator.comparing(categoryVo -> categoryVo.getSortOrder())
        ).collect(Collectors.toList());

        return ResponseVo.success(categoryList);
    }
    List<CategoryVo> getChildCategory(List<CategoryVo> allCategorys,CategoryVo category){
        //获得二级菜单
        List<CategoryVo> secondList = allCategorys.stream().filter(childCategory -> childCategory.getParentId().equals(category.getId())
        ).sorted(Comparator.comparing(categoryVo -> categoryVo.getSortOrder())
        ).collect(Collectors.toList());
        //对二级菜单递归遍历装填三、四...菜单
        List<CategoryVo> result = secondList.stream().map(categoryVo -> {
            categoryVo.setChildCategory(getChildCategory(allCategorys, categoryVo));
            return categoryVo;
        }).sorted(Comparator.comparing(categoryVo -> categoryVo.getSortOrder())
        ).collect(Collectors.toList());
        return result;
        /*
        合体写法
        return
        allCategorys.stream().filter(childCategory -> childCategory.getParentId().equals(category.getId())
        ).map(categoryVo -> {
            categoryVo.setChildCategory(getChildCategory(allCategorys, categoryVo));
            return categoryVo;
        }).sorted(Comparator.comparing(categoryVo -> categoryVo.getSortOrder())
        ).collect(Collectors.toList());
        */
    }
}