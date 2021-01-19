package com.jh.mall.service.impl;

import com.jh.mall.MallApplicationTests;
import com.jh.mall.dto.ShippingDto;
import com.jh.mall.service.ShipService;
import com.jh.mall.vo.ResponseVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


public class ShipServiceImplTest extends MallApplicationTests {
    @Autowired
    private ShipService shipService;
    ShippingDto shippingDto = new ShippingDto("hjh", "010", "13107613237", "Shanghai", "Shanghai", "jiali", "jiali", "10000");

    @Test
    public void add() {
        shipService.add(10,shippingDto);
    }

    @Test
    public void delete() {
        shipService.delete(10,7);
    }

    @Test
    public void update() {
        shipService.update(10,4,shippingDto);
    }

    @Test
    public void get() {
        ResponseVo responseVo = shipService.get(1, 10, 10);
        Object data = responseVo.getData();

    }
}