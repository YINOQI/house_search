package com.beibei.house_search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.domain.pojo.User;

import java.awt.geom.RectangularShape;

public interface UserService extends IService<User> {
    /**
     * 保存用户
     * @return 空
     */
    Result<String> saveUser(User user);

    /**
     * 获取用户信息
     * @return 用户信息
     */
    Result<User> getUser(Long userId);

    Result<User> login(Long userId);
}
