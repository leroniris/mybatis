package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

    void save(Student s);

    void insertRelationWithTeacher(@Param("student_id") Long student_id, @Param("teacher_id") Long teacher_id);

    void delete(Long id);

    void deleteRelationWithTeacher(Long student_id);


    Student get(Long id);
}
