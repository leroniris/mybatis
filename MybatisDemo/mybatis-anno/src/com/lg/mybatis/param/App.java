package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.User;
import com.lg.mybatis.param.mapper.UserMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class App {

    /**
     * 保存操作
     */
    @Test
    void testSave() {
        User user = new User();
        user.setName("楚留香");
        user.setSalary(new BigDecimal("900"));
        SqlSession session = MybatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.save(user);
        System.out.println(user);
        session.commit();
        session.close();
    }

    /**
     * 根据ID查询信息
     */
    @Test
    void testGet() {
        SqlSession session = MybatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.get(1L);
        System.out.println(user);
        session.close();

    }

    /**
     * 查询所有数据
     */
    @Test
    void testListAll() {
        SqlSession session = MybatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.listAll();
        users.forEach(System.out::println);
        session.close();
    }

    /**
     * 修改ID为5的用户
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(3L);
        user.setName("叶孤城");
        user.setSalary(new BigDecimal("800"));

        SqlSession session = MybatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.update(user);
        session.commit();
        session.close();

    }


    /**
     * 删除操作
     */
    @Test
    public void testDelete() {
        SqlSession session = MybatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        mapper.delete(9L);
        session.commit();
        session.close();

    }

}
