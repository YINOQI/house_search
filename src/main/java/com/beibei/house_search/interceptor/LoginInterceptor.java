package com.beibei.house_search.interceptor;

import cn.hutool.core.util.StrUtil;
import com.beibei.house_search.common.exception.ClientException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate stringRedisTemplate;
    public LoginInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        String user = stringRedisTemplate.opsForValue().get(token);
        if (StrUtil.isBlank(user)) {
            response.setStatus(401);
            throw new ClientException("用户未登录");
        }
        return true;
    }
}
