package com.cinfly.controller;

import com.cinfly.constant.PageResult;
import com.cinfly.constant.Result;
import com.cinfly.dto.CartItemsDto;
import com.cinfly.dto.OrderDto;
import com.cinfly.dto.OrderPageQueryDto;
import com.cinfly.entity.Orders;
import com.cinfly.service.IOrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.bouncycastle.crypto.engines.AESLightEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
@Tag(name = "订单相关接口")
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    @PostMapping
    @Operation(summary = "提交订单")
    public Result<Orders> submitOrder(@RequestBody OrderDto orderDto) {
        log.info("提交订单：{}", orderDto);
        Orders orders = ordersService.generateOrder(orderDto);
        return Result.success(orders);

    }

    @GetMapping("/{id}")
    @Operation(summary = "查询订单")
    public Result<Orders> getOrder(@PathVariable Long id) {
        log.info("查询订单：{}", id);
        Orders orders = ordersService.getById(id);
        return Result.success(orders);
    }

   /* @GetMapping("/user/{userId}")
    @Operation(summary = "查询用户订单")
    public Result<List<Orders>> getOrdersByUserId(@PathVariable Long userId) {
        log.info("查询用户订单：{}", userId);
        List<Orders> list = ordersService.lambdaQuery().eq(Orders::getUserId, userId).orderByDesc(Orders::getCreateTime).list();
        return Result.success(list);
    }*/

    @PutMapping("/{orderId}/{status}")
    @Operation(summary = "修改订单状态")
    public Result<String> updateOrderStatus(@PathVariable Long orderId, @PathVariable Integer status)
    {
        log.info("修改订单状态：{}", orderId);
        if(status==2)
        ordersService.lambdaUpdate().eq(Orders::getId, orderId).set(Orders::getStatus, status).update();
        else
        {
            ordersService.submit(orderId);
        }
        return Result.success();
    }
    @GetMapping("/list")
    @Operation(summary = "订单条件分页查询")
    public Result<PageResult>list(OrderPageQueryDto orderPageQueryDto)
    {
        log.info("订单条件分页查询：{}", orderPageQueryDto);
        PageResult pageResult=ordersService.listAll(orderPageQueryDto);
        return Result.success(pageResult);
    }

}
