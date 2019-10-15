package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Teacher;
import com.lg.mybatis.param.mapper.TeacherMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class App {


    /**
     * 测试一级缓存:SqlSession级别
     */
    @Test
    void testGet() {
        SqlSession session = MybatisUtil.getSession();
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.get(1L);
        Teacher teacher2 = teacherMapper.get(2L);
        System.out.println(teacher);
        session.clearCache(); //清空一级缓存
        System.out.println("-------------------------------------------");
        teacherMapper.get(1L);
        System.out.println(teacher2);
        teacherMapper.get(1L);
    }

    /**
     * 测试二级缓存:Mapper级别,跟命名空间namespace绑定
     */
    @Test
    void testSecondLevelCache() {
        SqlSession session = MybatisUtil.getSession();
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        teacherMapper.get(1L);
        session.close();
        System.out.println("-------------------------------------------");
        session = MybatisUtil.getSession();
        teacherMapper = session.getMapper(TeacherMapper.class);
        teacherMapper.get(1L);
        session.close();
        System.out.println("-------------------------------------------");
        session = MybatisUtil.getSession();
        teacherMapper = session.getMapper(TeacherMapper.class);
        teacherMapper.get(1L);
        session.close();
        System.out.println("-------------------------------------------");
        session = MybatisUtil.getSession();
        teacherMapper = session.getMapper(TeacherMapper.class);
        teacherMapper.get(1L);
        session.close();
    }


}
