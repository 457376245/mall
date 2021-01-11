package com.jh.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author JH
 * @Date 21/1/7 10:32
 * @Description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private String username;

    private String email;

    private String phone;

    private Integer role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}