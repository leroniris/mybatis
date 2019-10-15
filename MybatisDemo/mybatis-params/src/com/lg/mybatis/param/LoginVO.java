package com.lg.mybatis.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 封装登录信息
 * VO:Value Object
 */
@AllArgsConstructor
@Getter
public class LoginVO {
    private String username;
    private String password;
}
