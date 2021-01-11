package com.jh.mall.controller;

import com.jh.mall.consts.MallConsts;
import com.jh.mall.dto.UserLoginDto;
import com.jh.mall.dto.UserRegisterDto;
import com.jh.mall.enums.ResponseEnum;
import com.jh.mall.service.UserService;
import com.jh.mall.vo.ResponseVo;
import com.jh.mall.vo.UserVo;
import com.mysql.cj.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author JH
 * @Date 21/1/6 17:20
 * @Description TODO
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("")
    public ResponseVo<UserVo> userInfo(HttpSession session){
        UserVo userVo = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        if (userVo==null) return ResponseVo.error(ResponseEnum.NOT_LOGIN);
        return ResponseVo.success(userVo);
    }

    @PostMapping("/register")
    public ResponseVo register(@RequestBody @Valid UserRegisterDto userRegisterDto){

        return userService.register(userRegisterDto);
    }
    @PostMapping("/login")
    public ResponseVo<UserVo> login(@Valid @RequestBody UserLoginDto userLoginDto , BindingResult bindingResult, HttpServletRequest request,Session session){

        ResponseVo<UserVo> responseVo = userService.login(userLoginDto);
        if (responseVo.getData()!=null) {
            request.getSession().setAttribute(MallConsts.CURRENT_USER,responseVo.getData());
        }
        return responseVo;
    }

    @GetMapping("/logout")
    public ResponseVo logout(HttpSession session){
        Object attribute = session.getAttribute(MallConsts.CURRENT_USER);
        if (attribute==null) return ResponseVo.error(ResponseEnum.NOT_LOGIN);
        session.removeAttribute((MallConsts.CURRENT_USER));
        return ResponseVo.success();

    }
}