package com.spring.springbootcrud.Mapper;

import com.spring.springbootcrud.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_name=#{username}")
    public User getUserByName(String username);
    @Options(useGeneratedKeys = true,keyProperty = "user_id")
    @Insert("insert into user(user_name,user_password)values(#{user_name},#{user_password})")
    public int insertUser(User user);
}