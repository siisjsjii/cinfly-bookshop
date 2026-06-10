package com.cinfly.service;

import com.cinfly.constant.PageResult;
import com.cinfly.dto.OrderDto;
import com.cinfly.dto.OrderPageQueryDto;
import com.cinfly.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-10
 */
public interface IOrdersService extends IService<Orders> {

    Orders generateOrder(OrderDto orderDto);

    void submit(Long orderId);

    PageResult listAll(OrderPageQueryDto orderPageQueryDto);
}
