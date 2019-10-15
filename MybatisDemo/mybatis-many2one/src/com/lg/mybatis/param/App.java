package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Department;
import com.lg.mybatis.param.domain.Employee;
import com.lg.mybatis.param.mapper.DepartmentMapper;
import com.lg.mybatis.param.mapper.EmployeeMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

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

        //维护对象的关系
        e1.setDept(d);
        e2.setDept(d);

        SqlSession session = MybatisUtil.getSession();
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        //保存操作
        //先保存department，在保存employee
        departmentMapper.save(d);
        employeeMapper.save(e1);
        employeeMapper.save(e2);


        session.commit();
        session.close();
        System.out.println(d);
    }


    /**
     * 查询员工信息
     */
    @Test
    void testGet() {
        SqlSession session = MybatisUtil.getSession();
//        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);


        Employee e = employeeMapper.get(1L);
        //现在已经拥有部门的id
        /**
         * 让mybatis帮我发送这条额外的sql语句
         * Long deptId = e.getDept().getId();
         * Department d = departmentMapper.get(deptId);
         * e.setDept(d);
         */

//        System.out.println(e);

        System.out.println(e.getDept());
    }

    @Test
    void testListAll() {
        SqlSession session = MybatisUtil.getSession();

        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = employeeMapper.listAll();
        employeeList.forEach(System.out::println);

        session.close();
    }
}
