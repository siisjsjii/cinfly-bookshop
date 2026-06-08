package com.cinfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cinfly.entity.User;
import com.cinfly.exception.BusinessException;
import com.cinfly.mapper.UserMapper;
import com.cinfly.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinfly.utils.JwtUtil;
import com.cinfly.utils.PasswordEncoder;
import com.cinfly.vo.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public LoginUserVo login(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = this.getOne(wrapper);
        if(user==null)
        {
            throw new BusinessException("用户不存在");
        }
        //用户存在,校验密码,bcrypt解密
        if(!passwordEncoder.matches(password,user.getPassword()))
        {
            throw new BusinessException("密码错误");
        }
        //根据id username 生成token
        String token= jwtUtil.generateToken(user.getId(),username);
        return new LoginUserVo(user.getId(),username,token);
    }
}
