package com.jh.mall.exception;

import com.jh.mall.enums.ResponseEnum;
import com.jh.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author JH
 * @Date 21/1/6 16:55
 * @Description 用于统一异常返回格式
 */
@RestControllerAdvice
@Slf4j
public class RuntimeExceptionHandler {
    //RuntimeException处理
    @ExceptionHandler(RuntimeException.class)
    public ResponseVo handle(RuntimeException e){
         return ResponseVo.error(ResponseEnum.ERROR,e.getMessage());
    }
    //登入异常
    @ExceptionHandler(UserLoginException.class)
    public ResponseVo UserLoginHandle(){
        return ResponseVo.error(ResponseEnum.NOT_LOGIN);
    }
    //参数vaild校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo bingdingException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        log.error("注册提交参数有误:{}{}",exception.getBindingResult().getFieldError());
        return  ResponseVo.error(ResponseEnum.PARAM_ERROR,bindingResult);

    }
}