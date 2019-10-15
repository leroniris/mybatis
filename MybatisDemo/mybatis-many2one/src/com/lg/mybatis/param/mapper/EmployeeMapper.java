package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.Employee;

import java.util.List;

public interface EmployeeMapper {
    void save(Employee employee);

    Employee get(Long l);

    List<Employee> listAll();
}
