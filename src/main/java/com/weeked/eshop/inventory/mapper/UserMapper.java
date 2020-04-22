package com.weeked.eshop.inventory.mapper;

import com.weeked.eshop.inventory.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper
 * 2020-04-22
 */
@Mapper
public interface UserMapper {

    @Select("select name, age from user")
    @Results({
        @Result(column = "name", property = "name"),
        @Result(column = "age", property = "age")
    })
    List<User> findUserInfo();

}
