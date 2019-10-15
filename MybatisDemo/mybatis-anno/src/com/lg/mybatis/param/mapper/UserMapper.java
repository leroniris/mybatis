package com.lg.mybatis.param.mapper;

import com.lg.mybatis.param.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    /**
     * <insert id="save" useGeneratedKeys="true"
     * keyProperty="id"
     * parameterType="user">
     * insert into t_user(name,salary) values(#{name}, #{salary})
     * </insert>
     *
     * @param u
     */
    @Insert("insert into t_user(name,salary) values(#{name}, #{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User u);

    /**
     * <update id="update">
     * update t_user set name = #{name}, salary=#{salary} where id=#{id}
     * </update>
     *
     * @param u
     */
    @Update("update t_user set name = #{name}, salary=#{salary} where id=#{id}")
    void update(User u);


    /**
     * <delete id="delete" parameterType="java.lang.Long">
     * delete from t_user where id = #{id}
     * </delete>
     *
     * @param id
     */
    @Delete("delete from t_user where id = #{id}")
    void delete(Long id);


    /**
     * <select id="get" parameterType="java.lang.Long" resultType="user">
     * select id,name,salary from t_user where id=#{id}
     * </select>
     *
     * @param id
     * @return
     */
    @Select("select id as u_id,name as u_name,salary as u_salary from t_user where id = #{id}")
    //引用下面定义的BaseResultMap
    @ResultMap("BaseResultMap")
    User get(Long id);

    /**
     * <select id="listAll" resultType="user">
     * select id,name,salary from t_user
     * </select>
     *
     * @return
     */
    @Select("select id as u_id,name as u_name,salary as u_salary from t_user")
    @Results(id = "BaseResultMap", value = {
            @Result(column = "u_id", property = "id"),
            @Result(column = "u_name", property = "name"),
            @Result(column = "u_salary", property = "salary")
    })
    List<User> listAll();
}
