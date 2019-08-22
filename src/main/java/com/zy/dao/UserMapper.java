package com.zy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.zy.pojo.User;

@Component
@Mapper
public interface UserMapper {

@Select("select * from user")
public List<User> getAllUser();
}
