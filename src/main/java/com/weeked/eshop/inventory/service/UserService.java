package com.weeked.eshop.inventory.service;

import com.weeked.eshop.inventory.model.User;

import java.util.List;

/**
 * UserService
 * 2020-04-22
 */
public interface UserService {

    public User findUserInfo();

    public List<User> getCacheUserInfo();

}
