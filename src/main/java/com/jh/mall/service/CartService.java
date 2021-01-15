package com.jh.mall.service;

import com.jh.mall.dto.CartAddDto;
import com.jh.mall.dto.CartUpdateDto;
import com.jh.mall.vo.CartVo;
import com.jh.mall.vo.ResponseVo;

public interface CartService {
    ResponseVo<CartVo> addProductById(Integer uid, CartAddDto cartDto);
    ResponseVo<CartVo> list(Integer uid);
    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateDto updateDto);
    ResponseVo<CartVo> delete(Integer uid,Integer pid);
    ResponseVo<CartVo> selectAll(Integer uid);
    ResponseVo<CartVo> unSelectAll(Integer uid);
    ResponseVo<Integer> sum(Integer uid);

}
