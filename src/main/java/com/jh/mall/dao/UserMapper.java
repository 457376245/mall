package com.jh.mall.dao;

import com.jh.mall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
/*---------------------------------------------------------------*/
    int countByUsername(@Param("username") String username);

    User selectByUsername(@Param("username") String username);
}