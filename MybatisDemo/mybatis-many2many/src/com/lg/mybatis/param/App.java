package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Student;
import com.lg.mybatis.param.domain.Teacher;
import com.lg.mybatis.param.mapper.StudentMapper;
import com.lg.mybatis.param.mapper.TeacherMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class App {


    /**
     * 保存操作
     */
    @Test
    void testSave() {

        Teacher t1 = new Teacher();
        t1.setName("老师1");
        Teacher t2 = new Teacher();
        t2.setName("老师2");

        Student s1 = new Student();
        s1.setName("小明");

        Student s2 = new Student();
        s2.setName("小丽");

        //维护关系
        s1.getTeachers().add(t1);
        s1.getTeachers().add(t2);

        s2.getTeachers().add(t1);
        s2.getTeachers().add(t2);

        SqlSession session = MybatisUtil.getSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);

        studentMapper.save(s1);
        studentMapper.save(s2);

        teacherMapper.save(t1);
        teacherMapper.save(t2);

        //维护中间关系
        for(Teacher t : s1.getTeachers()) {
            studentMapper.insertRelationWithTeacher(s1.getId(), t.getId());
        }

        for(Teacher t : s2.getTeachers()) {
            studentMapper.insertRelationWithTeacher(s2.getId(), t.getId());
        }

        session.commit();
        session.close();

    }


    /**
     * 删除操作
     */
    @Test
    void testDelete() {
        SqlSession session = MybatisUtil.getSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        //先删除中间表数据,再删除自己
        studentMapper.deleteRelationWithTeacher(1L);

        studentMapper.delete(1L);

        session.commit();
        session.close();
    }


    /**
     * 根据ID查询学生信息
     */
    @Test
    void testGet() {
        SqlSession session = MybatisUtil.getSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        Student s = studentMapper.get(1L);
        System.out.println(s);
        //System.out.println(s.getTeachers());

        session.close();
    }

}
