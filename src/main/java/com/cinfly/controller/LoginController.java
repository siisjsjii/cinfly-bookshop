package com.cinfly.controller;

import com.cinfly.constant.Result;
import com.cinfly.entity.User;
import com.cinfly.service.IUserService;
import com.cinfly.vo.LoginUserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
@Slf4j
@Tag(name = "用户登录相关接口")
public class LoginController {
    @Autowired
    private IUserService userService;
        @PostMapping
        @Operation(summary = "用户登录")
        public Result<LoginUserVo> login(@RequestBody User user) {
            String username=user.getUsername();
            String password=user.getPassword();
            Integer status=user.getStatus();
            log.info("用户名：{}，密码：{}", username, password);
             LoginUserVo userVo=userService.login(username, password,status);
            return Result.success(userVo);
        }

}
