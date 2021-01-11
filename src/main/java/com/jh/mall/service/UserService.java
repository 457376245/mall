package com.jh.mall.service;

import com.jh.mall.dto.UserLoginDto;
import com.jh.mall.dto.UserRegisterDto;
import com.jh.mall.vo.ResponseVo;
import com.jh.mall.vo.UserVo;

/**
 * @Author JH
 * @Date 21/1/6 10:31
 * @Description TODO
 */
public interface UserService {
    ResponseVo<UserVo> register(UserRegisterDto user);

    ResponseVo<UserVo> login(UserLoginDto user);
}