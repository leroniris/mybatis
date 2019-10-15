package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Department;
import com.lg.mybatis.param.domain.Employee;
import com.lg.mybatis.param.mapper.DepartmentMapper;
import com.lg.mybatis.param.mapper.EmployeeMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class App {
    /**
     * 保存一个部门和两个员工
     */
    @Test
    void testSave() {
        Department d = new Department();
        d.setName("开发部");

        System.out.println(d);
        Employee e1 = new Employee();
        e1.setName("张三");
        Employee e2 = new Employee();
        e2.setName("李四");

        d.getEmps().add(e1);
        d.getEmps().add(e2);

        SqlSession session = MybatisUtil.getSession();
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        //保存操作
        //先保存department，在保存employee
        departmentMapper.save(d);
        e1.setDeptId(d.getId());
        e2.setDeptId(d.getId());
        employeeMapper.save(e1);
        employeeMapper.save(e2);


        session.commit();
        session.close();
        System.out.println(d);
    }


    /**
     * 查询十号部门的信息和包含的员工信息
     */
    @Test
    void testGet() {
        SqlSession session = MybatisUtil.getSession();
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);

        Department d = departmentMapper.get(1L);
        System.out.println(d);
//        System.out.println(d.getEmps());
    }


}
