package com.cinfly.controller;

import com.cinfly.constant.Result;
import com.cinfly.dto.PasswordDto;
import com.cinfly.entity.User;
import com.cinfly.mapper.UserMapper;
import com.cinfly.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name="用户相关接口")
public class UserController {
    @Autowired
    private IUserService userService;
    @PutMapping("/{userId}")
    @Operation(summary = "修改密码")
    public Result updatePassword(@PathVariable Integer userId, @RequestBody PasswordDto passwordDto) {
        log.info("修改密码：{}:{}", userId,passwordDto);
        userService.updatePassword(userId,passwordDto);
        return Result.success();
    }
}
