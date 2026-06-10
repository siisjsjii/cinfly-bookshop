package com.cinfly.service;

import com.cinfly.dto.AddCartDto;
import com.cinfly.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-10
 */
public interface ICartService extends IService<Cart> {

    void saveCart(AddCartDto addCartDto);
}
