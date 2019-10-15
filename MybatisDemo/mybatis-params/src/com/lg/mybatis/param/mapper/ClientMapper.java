package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.LoginVO;
import com.lg.mybatis.param.domain.Client;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ClientMapper {

    Client login1(LoginVO vo);

    Client login2(Map<String,Object> map);

    Client login3(@Param("username") String username, @Param("password") String password);

    Client login4(@Param("username") String username, @Param("password") String password);
}
