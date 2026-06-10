package com.cinfly.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cinfly.constant.Result;
import com.cinfly.dto.PasswordDto;
import com.cinfly.entity.User;
import com.cinfly.entity.Userinfo;
import com.cinfly.mapper.UserMapper;
import com.cinfly.service.IUserService;
import com.cinfly.service.IUserinfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name="用户相关接口")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserinfoService userinfoService;
    @PutMapping("/password/{userId}")
    @Operation(summary = "修改密码")
    public Result updatePassword(@PathVariable Long userId, @RequestBody PasswordDto passwordDto) {
        log.info("修改密码：{}:{}", userId,passwordDto);
        userService.updatePassword(userId,passwordDto);
        return Result.success();
    }
    @PutMapping("/{userId}")
    @Operation(summary = "修改基本信息")
    public Result updateUserInfo(@PathVariable Long userId, @RequestBody Userinfo userinfo)
    {
        log.info("修改用户基本信息：{}:{}", userId,userinfo);
        userinfo.setUpdateTime(LocalDateTime.now());
        userinfo.setId(null);
        userinfoService.update(userinfo,new LambdaUpdateWrapper<Userinfo>().eq(Userinfo::getUserId,userId));
        return Result.success();
    }
    @GetMapping("/{userId}")
    @Operation(summary = "查询用户信息")
    public Result<Userinfo> getUserInfo(@PathVariable Long userId)
    {
        log.info("查询用户信息：{}", userId);
        List<Userinfo> list = userinfoService.lambdaQuery().eq(Userinfo::getUserId, userId).list();
        Userinfo userinfo = list.get(0);
        userinfo.setPassword(null);
        return Result.success(userinfo);
    }
}
