package com.lg.mybatis.demo.mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //在开发中一个对象由很多字段，
    //现在有一个需求：把对象中部分数据查询出来，转换成JSON格式的字符串
    List<Map<String, Object>> listMap();
}