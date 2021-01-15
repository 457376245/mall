package com.jh.mall.service.impl;

import com.jh.mall.MallApplicationTests;
import com.jh.mall.dto.CartAddDto;
import com.jh.mall.service.CartService;
import com.jh.mall.vo.CartVo;
import com.jh.mall.vo.ResponseVo;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
@Slf4j
public class CartServiceImplTest extends MallApplicationTests {
    @Autowired
    private CartService cartService;

    @Test
    public void addProductById() {
        CartAddDto cartAddDto = new CartAddDto();
        cartAddDto.setProductId(27);
        cartAddDto.setSelected(true);
        ResponseVo<CartVo> cartVoResponseVo = cartService.addProductById(1, cartAddDto);
        log.info(cartVoResponseVo.toString());
    }

    @Test
    public void list(){
        cartService.list(1);
    }
}