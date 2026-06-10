package com.cinfly.mapper;

import com.cinfly.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author cinfly
 * @since 2026-06-10
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {

}
