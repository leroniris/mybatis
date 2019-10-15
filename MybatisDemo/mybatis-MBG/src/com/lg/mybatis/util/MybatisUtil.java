package com.lg.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory factory= null;
    static {
        try {
            //1：从classpath路径下加载全局配置文件
            InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
            //2：创建一个SqlSessionFactory对象，相当于DataSource
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //返回一个sqlSession对象
    public static SqlSession getSession() {
        return factory.openSession();
    }

    public static <T> T getMapper(Class<T> mapperClass) {
        return getSession().getMapper(mapperClass);
    }
}
