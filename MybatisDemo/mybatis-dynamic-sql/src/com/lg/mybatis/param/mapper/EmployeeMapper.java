package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeMapper {
    List<Employee> query(@Param("minSalary") BigDecimal minSalary);

    List<Employee> query2(@Param("minSalary") BigDecimal minSalary,
                          @Param("maxSalary") BigDecimal maxSalary,
                          @Param("deptId") Long deptId);


    void batchDelete(@Param("ids") Long[] ids);

    void batchSave(@Param("emps") List<Employee> emps);
}
