package com.lg.mybatis.param;


import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class App {

    /**
     * 查询单个用户信息
     *
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        SqlSession session = MybatisUtil.getSession();
        User user = session.selectOne("com.lg.mybatis.hello.UserMapper.get", 1L);
        System.out.println(user);
        session.close();
    }


    /**
     * 查询所有用户信息
     *
     * @throws IOException
     */
    @Test
    public void testListAll() throws IOException {
        SqlSession session = MybatisUtil.getSession();
        List<User> users = session.selectList("com.lg.mybatis.hello.UserMapper.listAll");
        users.forEach(System.out::println);
        session.close();
    }


}
