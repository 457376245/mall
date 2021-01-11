package com.jh.mall.service.impl;

import com.jh.mall.MallApplicationTests;
import com.jh.mall.dto.UserLoginDto;
import com.jh.mall.dto.UserRegisterDto;
import com.jh.mall.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@Transactional
public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private UserService userService;
    private UserRegisterDto userRegisterDto=new UserRegisterDto("username","password","email",1);


    @Before
    @Test
    public void register() {
        userRegisterDto.setRole(null);
        userService.register(userRegisterDto);
    }

    @Test
    public void login() {
        userService.login(new UserLoginDto(userRegisterDto.getUsername(),userRegisterDto.getPassword()));
    }
}