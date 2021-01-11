package com.jh.mall.interceptor;

import com.jh.mall.consts.MallConsts;
import com.jh.mall.exception.UserLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author JH
 * @Date 21/1/7 15:17
 * @Description TODO
 */
@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute(MallConsts.CURRENT_USER)==null){
            throw new UserLoginException();
        }
        return true;
    }
}