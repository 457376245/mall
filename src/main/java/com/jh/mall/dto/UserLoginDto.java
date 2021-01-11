package com.jh.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author JH
 * @Date 21/1/7 10:37
 * @Description 用户登入参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
}