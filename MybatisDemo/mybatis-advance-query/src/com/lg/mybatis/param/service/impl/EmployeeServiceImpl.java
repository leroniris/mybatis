package com.lg.mybatis.param.service.impl;

import com.lg.mybatis.param.domain.Employee;
import com.lg.mybatis.param.mapper.EmployeeMapper;
import com.lg.mybatis.param.query.EmployeeQueryObject;
import com.lg.mybatis.param.query.PageResult;
import com.lg.mybatis.param.query.QueryObject;
import com.lg.mybatis.param.service.IEmployeeService;
import com.lg.mybatis.util.MybatisUtil;

import java.util.Collections;
import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService {

    private EmployeeMapper employeeMapper = MybatisUtil.getMapper(EmployeeMapper.class);
    @Override
    public PageResult query(QueryObject qo) {
        int rows = employeeMapper.queryForCount(qo);
        if(rows == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        List<Employee> result = employeeMapper.queryForList(qo);
        return new PageResult(result, rows, qo.getCurrentPage(), qo.getPageSize());
    }
}
