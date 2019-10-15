package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.Employee;
import com.lg.mybatis.param.query.EmployeeQueryObject;
import com.lg.mybatis.param.query.QueryObject;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> queryForList(QueryObject qo);

    int queryForCount(QueryObject qo);
}
