package com.weeked.eshop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.weeked.eshop.inventory.dao.RedisDAO;
import com.weeked.eshop.inventory.mapper.UserMapper;
import com.weeked.eshop.inventory.model.User;
import com.weeked.eshop.inventory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserServiceImpl
 * 2020-04-22
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisDAO redisDAO;


    @Override
    public User findUserInfo() {
        redisDAO.set("cache_user_lisi", "{'name':'lisi','age':28}");

        String userJSON = redisDAO.get("cache_user_lisi");
        JSONObject userJSONObject = JSONObject.parseObject(userJSON);

        User user = new User();
        user.setName(userJSONObject.getString("name"));
        user.setAge(userJSONObject.getInteger("age"));

        return user;
    }

    @Override
    public List<User> getCacheUserInfo() {
        return userMapper.findUserInfo();
    }
}
