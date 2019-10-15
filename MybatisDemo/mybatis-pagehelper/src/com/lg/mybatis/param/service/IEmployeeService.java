package com.lg.mybatis.param.service;

import com.lg.mybatis.param.query.PageResult;
import com.lg.mybatis.param.query.QueryObject;

public interface IEmployeeService {
    PageResult query(QueryObject qo);
}
