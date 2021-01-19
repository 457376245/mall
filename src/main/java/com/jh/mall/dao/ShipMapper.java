package com.jh.mall.dao;

import com.jh.mall.pojo.Ship;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShipMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid,@Param("id") Integer id);

    int insert(Ship record);

    int insertSelective(Ship record);

    Ship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ship record);

    int updateByPrimaryKey(Ship record);

    List<Ship> getAllByUserId(@Param("uid") Integer uid);
}