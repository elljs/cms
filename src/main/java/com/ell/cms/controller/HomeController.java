package com.ell.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ell.cms.model.User;
import com.ell.cms.service.impl.UserService;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.lang.Console;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@SaIgnore
@RestController
@Tag(name = "首页")
public class HomeController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "首页")
    @Cacheable(value = "userCache", key = "#root.targetClass + ':' + #root.methodName")
    public User home() {

        redisTemplate.opsForValue().set("test", "redisson");

        Console.log(redisTemplate.opsForValue().get("test"));

        var user = userService.getById(2L);
        if (user == null) {
            user = new User();
            user.setNickname("RoyLin");
            userService.save(user);
        }

        System.out.println(user);

        return user;
    }
}
