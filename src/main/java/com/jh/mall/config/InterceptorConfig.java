package com.jh.mall.config;

import com.jh.mall.interceptor.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author JH
 * @Date 21/1/7 15:22
 * @Description TODO
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .excludePathPatterns("/**");
                //.addPathPatterns("/**")
                //.excludePathPatterns("/user/login");

    }
}