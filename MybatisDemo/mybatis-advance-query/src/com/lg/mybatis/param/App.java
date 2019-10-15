package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Employee;
import com.lg.mybatis.param.mapper.EmployeeMapper;
import com.lg.mybatis.param.query.EmployeeQueryObject;
import com.lg.mybatis.param.query.PageResult;
import com.lg.mybatis.param.service.IEmployeeService;
import com.lg.mybatis.param.service.impl.EmployeeServiceImpl;
import com.lg.mybatis.util.MybatisUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class App {
    /**
     * 需求：按照员工的关键字，工资范围，所属部门来查询
     */

    //高级查询
    @Test
    void test1() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);

        EmployeeQueryObject qo = new EmployeeQueryObject();
//        qo.setKeyword(" ");
        qo.setMinSalary(new BigDecimal("1000"));
        qo.setMaxSalary(new BigDecimal("2000"));
        qo.setDeptId(1L);
        List<Employee> list = mapper.queryForList(qo);
        list.forEach(System.out::println);
    }


    @Test
    void test2() {
        EmployeeMapper mapper = MybatisUtil.getMapper(EmployeeMapper.class);

        EmployeeQueryObject qo = new EmployeeQueryObject();
//        qo.setKeyword(" ");
        qo.setMinSalary(new BigDecimal("1000"));
        qo.setMaxSalary(new BigDecimal("2000"));
        qo.setDeptId(1L);
        int count = mapper.queryForCount(qo);
        System.out.println(count);
    }


    /**
     * 测试分页查询
     */
    @Test
    void test3() {
        IEmployeeService service = new EmployeeServiceImpl();
        EmployeeQueryObject qo = new EmployeeQueryObject();
//        qo.setMinSalary(new BigDecimal("1000"));
//        qo.setMaxSalary(new BigDecimal("2000"));
//        qo.setDeptId(1L);
        PageResult result = service.query(qo);
        System.out.println(result.getTotalCount());
        for(Object o : result.getResult()) {
            System.out.println(o);
        }
    }
}
