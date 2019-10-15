package com.lg.mybatis.param.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Teacher implements Serializable {
    private Long id;
    private String name;
}
