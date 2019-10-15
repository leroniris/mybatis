package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.Department;

public interface DepartmentMapper {
    void save(Department department);

    Department get(Long id);
}
