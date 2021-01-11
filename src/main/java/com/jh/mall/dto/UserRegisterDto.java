package com.jh.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author JH
 * @Date 21/1/6 16:41
 * @Description 用户注册接收参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    private Integer role;
}