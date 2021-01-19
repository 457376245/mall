package com.jh.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jh.mall.dao.ShipMapper;
import com.jh.mall.dto.ShippingDto;
import com.jh.mall.enums.ResponseEnum;
import com.jh.mall.pojo.Ship;
import com.jh.mall.service.ShipService;
import com.jh.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author JH
 * @Date 21/1/15 14:22
 * @Description TODO
 */
@Service
public class ShipServiceImpl implements ShipService {
    @Resource
    private ShipMapper shipMapper;

    @Override
    public ResponseVo add(Integer uid, ShippingDto shippingDto) {
        Ship ship = new Ship();
        BeanUtils.copyProperties(shippingDto,ship);
        ship.setUserId(uid);
        ship.setCreateTime(LocalDateTime.now());
        ship.setUpdateTime(LocalDateTime.now());
        int res = shipMapper.insertSelective(ship);
        if (res!=1) return ResponseVo.error(ResponseEnum.SHIP_CREATE_ERROR);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo delete(Integer uid, Integer shippingId) {
        int res = shipMapper.deleteByPrimaryKey(uid,shippingId);
        if (res!=1) return ResponseVo.error(ResponseEnum.SHIP_DEL_ERROR);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo update(Integer uid,Integer shippingId, ShippingDto shippingDto) {
        Ship ship = new Ship();
        BeanUtils.copyProperties(shippingDto, ship);
        ship.setId(shippingId);
        ship.setUserId(uid);
        ship.setUpdateTime(LocalDateTime.now());
        int res = shipMapper.updateByPrimaryKeySelective(ship);
        if (res!=1) return ResponseVo.error(ResponseEnum.SHIP_UPDATE_ERROR);
        return ResponseVo.success();
    }

    @Override
    public ResponseVo get(Integer pageNum,Integer pageSize,Integer uid) {
        PageHelper.startPage(pageNum,pageSize);
        List<Ship> ships = shipMapper.getAllByUserId(uid);
        PageInfo<Ship> pageInfo = new PageInfo<>();
        pageInfo.setList(ships);
        return ResponseVo.success(pageInfo);
    }
}