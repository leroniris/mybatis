package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.User;

import java.util.List;

public interface UserMapper {

    void save(User u);

    void update(User u);

    void delete(Long id);

    User get(Long id);

    List<User> listAll();
}
