package com.lg.mybatis.param;


import com.lg.mybatis.param.domain.Client;
import com.lg.mybatis.param.mapper.ClientMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class App {

    /**
     * 方式一：封装成一个对象
     */
    @Test
    public void testLogin() {
        LoginVO vo = new LoginVO("will", "123456");

        SqlSession session = MybatisUtil.getSession();
        ClientMapper mapper = session.getMapper(ClientMapper.class);

        Client client = mapper.login1(vo);

        session.close();
        System.out.println(client);
    }

    /**
     * 方式二：通过Map
     */
    @Test
    public void testLogin2() {
        Map<String, Object> map = new HashMap<String, Object>(){
            {
                this.put("username", "will");
                this.put("password", "123456");
            }
        };

        SqlSession session = MybatisUtil.getSession();
        ClientMapper mapper = session.getMapper(ClientMapper.class);
        Client client = mapper.login2(map);
        System.out.println(client);

        session.close();
    }

    /**
     * 方式三：使用Param注解，底层原理方式二，Mybatis帮我们使用map封装,@Param中的字符串表示map中的key
     */
    @Test
    public void testLogin3() {
        SqlSession session = MybatisUtil.getSession();
        ClientMapper mapper = session.getMapper(ClientMapper.class);
        Client client = mapper.login3("will", "123456");
        System.out.println(client);
        session.close();
    }
}
