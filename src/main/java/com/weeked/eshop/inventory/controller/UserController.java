package com.weeked.eshop.inventory.controller;

import com.weeked.eshop.inventory.model.User;
import com.weeked.eshop.inventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController
 * 2020-04-22
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    public User getUserInfo(){
        User user = userService.findUserInfo();
        return user;
    }

    @RequestMapping("/getCacheUserInfo")
    public List<User> getCacheUserInfo(){
        List<User> user = userService.getCacheUserInfo();
        return user;
    }

}
