package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.Teacher;

public interface TeacherMapper {

    void save(Teacher t);

    Teacher selectByStudentId(Long student_id);

}
