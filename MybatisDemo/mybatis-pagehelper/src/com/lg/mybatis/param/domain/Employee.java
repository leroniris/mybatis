package com.lg.mybatis.param.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Employee {
	private Long id;
	private String name;
	private String sn;
	private BigDecimal salary;
	private Long deptId;
}
