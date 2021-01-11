package com.jh.mall.enums;

import lombok.Getter;

/**
 *
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1);
    Integer code;
    RoleEnum(int code) {
        this.code=code;
    }
}
