package com.lg.mybatis.param.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Employee {
    private Long id;
    private String name;

    private Long deptId;//员工所在部门的ID

}
