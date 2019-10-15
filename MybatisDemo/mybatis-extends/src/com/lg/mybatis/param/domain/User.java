package com.lg.mybatis.param.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

@Data
@Alias("user")
public class User {
    private Long id;
    private String name;
    private BigDecimal salary;
}
