package com.jh.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    ERROR(-1,"服务器错误"),
    SUCCESS(0,"成功"),
    PASSWORD_ERROR(1,"密码错误"),
    USER_EXIST(2,"用户已存在"),
    NOT_LOGIN(10,"未登入"),
    PARAM_ERROR(3,"参数有误"),
    USER_ERROR(4,"用户名或密码错误");
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
