package com.jh.mall.enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    ERROR(-1,"服务器错误"),
    SUCCESS(0,"成功"),
    PASSWORD_ERROR(11,"密码错误"),
    USER_EXIST(12,"用户已存在"),
    NOT_LOGIN(10,"未登入"),
    PARAM_ERROR(13,"参数有误"),
    USER_ERROR(14,"用户名或密码错误"),
    PRODUCT_ERROR(21,"该商品已下架或删除");
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
