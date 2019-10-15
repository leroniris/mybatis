package com.lg.mybatis;

import com.lg.mybatis.demo.domain.SystemUserExample;
import com.lg.mybatis.demo.domain.SystemUserKey;
import com.lg.mybatis.demo.mapper.SystemUserMapper;
import com.lg.mybatis.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class App {

    @Test
    void test1() {
        SqlSession session = MybatisUtil.getSession();
        SystemUserMapper mapper = session.getMapper(SystemUserMapper.class);

        SystemUserKey key = new SystemUserKey();
        key.setId(2L);
        //根据ID查询2的数据
        mapper.selectByPrimaryKey(key);

        //查询userType在1-3之间的数据
        SystemUserExample example = new SystemUserExample();
        SystemUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserTypeBetween(1,3);
        mapper.selectByExample(example);

        //查询userType在1-3之间的数据,且名字中带有老字的
        criteria.andUsernameLike("%老%");
        mapper.selectByExample(example);

    }



}
