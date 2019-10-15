package com.lg.mybatis.param.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Data
public class Client {
    private Long id;
    private String username;
    private String password;
}
