package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Employee;
import com.lg.mybatis.param.mapper.EmployeeMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.MARSHAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class App {

    /**
     * 需求1：查询工资大于等于1000的员工
     */
    @Test
    void test1() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal("1000");
        List<Employee> es =  mapper.query(minSalary);
        es.forEach(System.out::println);

    }


    /**
     * 需求2：查询工资1000-2000的员工
     */
    @Test
    void test2() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
        BigDecimal minSalary = new BigDecimal("1000");
        BigDecimal maxSalary = new BigDecimal("2000");
        List<Employee> es =  mapper.query2(null, maxSalary, null);
        es.forEach(System.out::println);

    }


    /**
     * 需求3：查询指定部门的员工
     */
    @Test
    void test3() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
        List<Employee> es =  mapper.query2(null, null, null);
        es.forEach(System.out::println);

    }


    /**
     * 需求4：批量删除
     */
    @Test
    void test4() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
        mapper.batchDelete(new Long[] {1L, 2L, 3L});

    }

    /**
     * 需求5：批量插入
     */
    @Test
    void test5() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);
        List<Employee> list = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setName("李8");
        e1.setSn("sa");
        e1.setSalary(new BigDecimal("9000"));
        e1.setDeptId(3L);
        list.add(e1);

        Employee e2 = new Employee();
        e1.setName("李9");
        e1.setSn("sa");
        e1.setSalary(new BigDecimal("10000"));
        e1.setDeptId(3L);
        list.add(e2);

        mapper.batchSave(list);
    }
}
