package com.jh.mall.service;

import com.jh.mall.dto.ShippingDto;
import com.jh.mall.vo.ResponseVo;

/**
 * @Author JH
 * @Date 21/1/15 14:22
 * @Description TODO
 */
public interface ShipService {
    ResponseVo add(Integer uid,ShippingDto shippingDto);
    ResponseVo delete(Integer uid,Integer shippingId);
    ResponseVo update(Integer uid,Integer shippingId,ShippingDto shippingDto);
    ResponseVo get(Integer pageNum,Integer pageSize,Integer uid);
}