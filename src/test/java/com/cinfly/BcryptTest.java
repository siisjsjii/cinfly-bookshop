package com.cinfly;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cinfly.entity.User;
import com.cinfly.mapper.UserMapper;
import com.cinfly.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BcryptTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test()
    {
        String password="123456";
        String encode = passwordEncoder.encode(password);
        //userMapper.updateById(new User(1, "cinfly", encode, LocalDateTime.now(),1));
       // userMapper.insertOrUpdate(new User(2, "sky", encode, LocalDateTime.now(),1));

    }
}
