package com.lg.mybatis.param;


import com.lg.mybatis.util.MybatisUtil;
import lombok.Cleanup;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

public class App {

    /**
     * 查询单个用户信息
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        //1：从classpath路径下加载全局配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2：创建一个SqlSessionFactory对象，相当于DataSource
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3：创建一个SqlSession对象，相当于Connection
        SqlSession session = factory.openSession();
        //4：具体的操作，增删改查
        User user = session.selectOne("com.lg.mybatis.hello.UserMapper.get", 1L);
        System.out.println(user);
        //5：关闭session
        session.close();
    }


    /**
     * 查询所有用户信息
     * @throws IOException
     */
    @Test
    public void testListAll() throws IOException {
        SqlSession session = null;
        try {
            session = MybatisUtil.getSession();
            //3：创建一个SqlSession对象，相当于Connection
            //4：具体的操作，增删改查
            List<User> users = session.selectList("com.lg.mybatis.hello.UserMapper.listAll");
            users.forEach(System.out::println);
        } finally {
            //5：关闭session
            session.close();
        }
    }


    /**
     * 查询所有用户信息
     * @throws IOException
     */
    @Test
    public void testListAll2() throws IOException {
        @Cleanup
        SqlSession session = MybatisUtil.getSession();
        List<User> users = session.selectList("com.lg.mybatis.hello.UserMapper.listAll");
        users.forEach(System.out::println);
    }


    /**
     * 修改ID为3的用户
     */
    @Test
    void testUpdate() {
        User u = new User();
        u.setName("叶孤城");
        u.setSalary(new BigDecimal(900));
        u.setId(3L);
        SqlSession session = MybatisUtil.getSession();

        session.update("com.lg.mybatis.hello.UserMapper.update", u);
        //提交事务
        session.commit();
        session.close();
    }

    /**
     * 删出ID为4的用户
     */
    @Test
    void testDelete() {
        SqlSession session = MybatisUtil.getSession();

        session.delete("com.lg.mybatis.hello.UserMapper.delete", 4L);
        //提交事务
        session.commit();
        session.close();
    }


    @Test
    void testSave() {
        SqlSession session = MybatisUtil.getSession();
        //
        User user = new User();
        user.setName("楚留香");
        user.setSalary(new BigDecimal(800));
        System.out.println(user);
        session.insert("com.lg.mybatis.hello.UserMapper.save", user);

        session.commit();
        session.close();
        System.out.println(user);
    }

    private static Logger log = Logger.getLogger(App.class);

    @Test
    void testLog() {
        //如果日志级别是INFO,就输出
        if(log.isInfoEnabled()) {
            log.info("银行转账操作");
        }
        if(log.isDebugEnabled()) {
            log.debug("查询数据库");
        }
        if(log.isTraceEnabled()) {
            log.trace("连接数据库");
        }
        if(log.isTraceEnabled()) {
            log.trace("获取连接对象");
        }
        if(log.isDebugEnabled()) {
            log.debug("转账。。。");
        }
        if(log.isInfoEnabled()) {
            log.info("银行转账成功");
        }
    }
}
