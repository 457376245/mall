package com.jh.mall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jh.mall.enums.ResponseEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

/**
 * @Author JH
 * @Date 21/1/6 16:42
 * @Description TODO
 */
//不返回空的字段
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;

    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public ResponseVo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseVo(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public static ResponseVo success(){
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(),ResponseEnum.SUCCESS.getMsg());
    }
    public static <T> ResponseVo success(T data){
        return new ResponseVo(ResponseEnum.SUCCESS.getCode(),data);
    }
    public static  ResponseVo error(ResponseEnum responseEnum){
        return new ResponseVo(responseEnum.getCode(),responseEnum.getMsg());
    }
    public static  ResponseVo error(ResponseEnum responseEnum,String msg){
        return new ResponseVo(responseEnum.getCode(),msg);
    }
    public static  ResponseVo error(ResponseEnum responseEnum,BindingResult bindingResult){
        return new ResponseVo(responseEnum.getCode(),bindingResult.getFieldError().getField()+" "+bindingResult.getFieldError().getDefaultMessage());
    }
}