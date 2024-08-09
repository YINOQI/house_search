package com.beibei.house_search.controller;

import com.beibei.house_search.domain.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
//@Api(tags  = "用户接口")
public class UserController {

    @GetMapping
//    @ApiOperation(value = "用户测试",notes = "用户测试notes")
    public User test(){
        User user = new User();
        user.setUserId(10L);
        return user;
    }
}
