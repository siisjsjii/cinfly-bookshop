package com.cinfly.service;

import com.cinfly.dto.PasswordDto;
import com.cinfly.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinfly.vo.LoginUserVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-08
 */
public interface IUserService extends IService<User> {

    LoginUserVo login(String username, String password,Integer status);

    void updatePassword(Long id, PasswordDto passwordDto);
}
