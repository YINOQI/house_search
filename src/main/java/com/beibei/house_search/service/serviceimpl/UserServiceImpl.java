package com.beibei.house_search.service.serviceimpl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.beibei.house_search.common.result.Result;
import com.beibei.house_search.common.result.Results;
import com.beibei.house_search.domain.pojo.User;
import com.beibei.house_search.mapper.UserMapper;
import com.beibei.house_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public Result<String> saveUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserId(Long.parseLong(uuid));
        stringRedisTemplate.opsForValue().set(uuid,JSONUtil.toJsonStr(user));
        this.save(user);
        return Results.success("保存用户信息成功");
    }

    @Override
    public Result<User> getUser(Long userId) {
        String userStr = stringRedisTemplate.opsForValue().get(userId.toString());
        if (StrUtil.isNotBlank(userStr)) {
            return Results.success(JSONUtil.toBean(userStr,User.class));
        }else {
            User user = this.getById(userId);
            return Results.success(user);
        }
    }

    @Override
    public Result<User> login(Long userId) {
        String userStr = stringRedisTemplate.opsForValue().get(userId);
        if (StrUtil.isBlank(userStr)) {
            User user = this.getById(userId);
            stringRedisTemplate.opsForValue().set(userId.toString(), JSONUtil.toJsonStr(user));
            return Results.success(user);
        }else {
            return Results.success(JSONUtil.toBean(userStr,User.class));
        }
    }
}
