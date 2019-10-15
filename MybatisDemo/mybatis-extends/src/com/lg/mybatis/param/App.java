package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.User;
import com.lg.mybatis.param.mapper.UserMapper;
import com.lg.mybatis.param.proxy.MyMapperProxy;
import com.lg.mybatis.util.MybatisUtil;
import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class App {

    /**
     * 查询单个用户信息
     *
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        SqlSession session = MybatisUtil.getSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.get(3L);
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
        UserMapper mapper = session.getMapper(UserMapper.class);
        System.out.println(mapper.getClass());
        List<User> users = mapper.listAll();
        users.forEach(System.out::println);
        session.close();
    }


    /**
     * 查询所有用户信息
     *
     * @throws IOException
     */
    @Test
    public void testQueryForMap() throws IOException {
        SqlSession session = null;
        try {
            session = MybatisUtil.getSession();
            //3：创建一个SqlSession对象，相当于Connection
            //4：具体的操作，增删改查
            List<Map<String, Object>> users = session.selectList("com.lg.mybatis.hello.UserMapper.queryForMap");
            users.forEach(System.out::println);
        } finally {
            //5：关闭session
            session.close();
        }
    }


    /**
     * 查询所有用户信息
     *
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
        if (log.isInfoEnabled()) {
            log.info("银行转账操作");
        }
        if (log.isDebugEnabled()) {
            log.debug("查询数据库");
        }
        if (log.isTraceEnabled()) {
            log.trace("连接数据库");
        }
        if (log.isTraceEnabled()) {
            log.trace("获取连接对象");
        }
        if (log.isDebugEnabled()) {
            log.debug("转账。。。");
        }
        if (log.isInfoEnabled()) {
            log.info("银行转账成功");
        }
    }


    @Test
    void testMockMybatisProxy() throws Exception {
        SqlSession session =  MybatisUtil.getSession();
        MyMapperProxy<UserMapper> mapperProxy = new MyMapperProxy<>();
        mapperProxy.setMapperInterface(UserMapper.class);
        mapperProxy.setSession(session);
        UserMapper proxy = mapperProxy.getProxyObject();
        System.out.println(proxy.getClass());
        List<User> users = proxy.listAll();
        users.forEach(System.out::println);
    }
}
