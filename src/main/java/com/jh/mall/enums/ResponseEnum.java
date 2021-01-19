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
    PRODUCT_NOT_EXIST(21,"商品不存在"),
    PRODUCT_ERROR(22,"该商品已下架或删除"),
    CART_PRODUCT_NOT_EXIST(31,"购物车商品不存在"),
    SHIP_CREATE_ERROR(41,"新建地址失败"),
    SHIP_DEL_ERROR(42,"删除地址失败"),
    SHIP_UPDATE_ERROR(43,"更新地址失败"),
    SHIP_GET_ERROR(44,"获取地址失败"),;
    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
