package com.jh.mall.service.impl;

import com.jh.mall.dao.UserMapper;
import com.jh.mall.dto.UserLoginDto;
import com.jh.mall.dto.UserRegisterDto;
import com.jh.mall.enums.ResponseEnum;
import com.jh.mall.pojo.User;
import com.jh.mall.service.UserService;
import com.jh.mall.vo.ResponseVo;
import com.jh.mall.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author JH
 * @Date 21/1/6 10:32
 * @Description TODO
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo<UserVo> register(UserRegisterDto userRegisterDto) {
        //校验参数
       /* if (bindingResult.hasErrors()){
            log.error("注册提交参数有误:{}{}",bindingResult.getFieldError().getField(),bindingResult.getFieldError());
            return  ResponseVo.error(bindingResult);
        }*/
        //判断用户是否存在
        int count = userMapper.countByUsername(userRegisterDto.getUsername());
        if (count>0) return ResponseVo.error(ResponseEnum.USER_EXIST);

        //插入新用户
        User user = new User();
        BeanUtils.copyProperties(userRegisterDto,user);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        int result = userMapper.insert(user);
        if (result!=1) return ResponseVo.error(ResponseEnum.ERROR);
        log.info(ResponseVo.success().toString());
        return ResponseVo.success();
    }

    @Override
    public ResponseVo<UserVo> login(UserLoginDto userLoginDto) {
        User user = userMapper.selectByUsername(userLoginDto.getUsername());
        if (user==null) return ResponseVo.error(ResponseEnum.USER_ERROR);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(userLoginDto.getPassword().getBytes()))) return ResponseVo.error(ResponseEnum.USER_ERROR);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return ResponseVo.success(userVo);
    }
}