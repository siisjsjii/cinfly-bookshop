package com.cinfly.service.impl;

import com.cinfly.entity.OrderInfo;
import com.cinfly.mapper.OrderInfoMapper;
import com.cinfly.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-10
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
