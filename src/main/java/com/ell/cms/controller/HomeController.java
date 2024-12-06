package com.ell.cms.controller;

import java.util.concurrent.TimeUnit;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ell.cms.dto.UserDto;
import com.ell.cms.model.User;
import com.ell.cms.service.impl.UserService;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.hutool.core.lang.Console;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SaIgnore
@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "首页")
public class HomeController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RocketMQTemplate rocketTemplate;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "首页")
    @Cacheable(value = "userCache", key = "#root.targetClass + ':' + #root.methodName")
    public User home() {
        RLock lock = redissonClient.getLock("myLock");
        try {
            // 尝试获取锁，等待时间100ms，锁定时间10秒
            if (lock.tryLock(100, 10, TimeUnit.SECONDS)) {
                try {
                    // 加锁成功，执行业务逻辑
                    System.out.println("锁定成功，正在执行关键任务...");
                    // 模拟任务执行
                    redisTemplate.opsForValue().set("test", "redisson");

                    Console.log(redisTemplate.opsForValue().get("test"));

                    var user = userService.getById(2L);
                    if (user == null) {
                        user = new User();
                        user.setNickname("RoyLin");
                        userService.save(user);
                    }
                    return user;
                } finally {
                    // 释放锁
                    lock.unlock();
                    System.out.println("任务完成，已释放锁");
                }
            } else {
                System.out.println("无法获取锁，其他线程正在执行该任务");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping
    public String createUser(@RequestBody @Valid UserDto user) {
        // 这里的user对象已经经过了校验，如果校验不通过，则会抛出异常
        return "User created successfully!";
    }

    @GetMapping("/send/msg")
    public String sendMsg() {
        try {
            rocketTemplate.convertAndSend("email-topic", "test message");
            System.out.println("消息发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
