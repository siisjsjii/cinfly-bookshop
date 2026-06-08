package com.cinfly.mapper;

import com.cinfly.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author cinfly
 * @since 2026-06-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
