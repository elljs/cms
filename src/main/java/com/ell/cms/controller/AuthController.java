package com.ell.cms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "授权")
@SecurityRequirement(name = "jwt")
public class AuthController {
    @GetMapping("login")
    @Operation(summary = "登录")
    public SaResult doLogin() {
        // 第1步，先登录上
        StpUtil.login(1L);
        // 第2步，获取 Token 相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，在会话中设置自定义数据
        StpUtil.getSession().set("user", 1L);
        // 第4步，返回给前端
        return SaResult.data(tokenInfo);
    }

    @GetMapping("logout")
    @Operation(summary = "登出")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @GetMapping("status")
    @Operation(summary = "状态")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }
}
