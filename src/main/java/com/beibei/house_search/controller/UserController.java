package com.beibei.house_search.controller;

import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.pojo.User;
import com.beibei.house_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
//@Api(tags  = "用户接口")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Result<User> getUser(Long useId){
        return userService.getUser(useId);
    }

    @PostMapping("/save")
    public Result<String> saveUser(User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public Result<User> login(Long userId){
        return userService.login(userId);
    }

}
