package com.cinfly.controller;

import com.cinfly.constant.Result;
import com.cinfly.dto.AddCartDto;
import com.cinfly.entity.Cart;
import com.cinfly.service.ICartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
@Tag(name = "购物车相关接口")
public class CartController {
    @Autowired
    private ICartService cartService;

    @PostMapping
    @Operation(summary = "添加购物车")
    public Result addCart(@RequestBody AddCartDto addCartDto)
    {
        log.info("添加购物车：{}", addCartDto);
        cartService.saveCart(addCartDto);
        return Result.success();
    }
    @GetMapping("/{id}")
    @Operation(summary = "查询购物车")
    public Result<List<Cart>> getCart(@PathVariable Long id)
    {
        log.info("查询购物车：{}", id);
        List<Cart> list = cartService.lambdaQuery().eq(Cart::getUserId, id).list();
        return Result.success(list);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "删除商品")
    public Result deleteCart(@PathVariable Long id)
    {
        log.info("删除购物车：{}", id);
        cartService.removeById(id);
        return Result.success();
    }
    @PutMapping("/{id}/quantity")
    @Operation(summary = "修改商品数量")
    public Result updateCart(@PathVariable Long id, @RequestBody Cart cart)
    {
        log.info("修改购物车：{}", cart);
        cart.setId(id);
        cartService.updateById( cart);
        return Result.success();
    }
    @DeleteMapping("/{userid}/clear")
    @Operation(summary = "清空购物车")
    public Result clearCart(@PathVariable Long userid)
    {
        log.info("清空购物车：{}", userid);
        cartService.lambdaUpdate().eq(Cart::getUserId, userid).remove();
        return Result.success();
    }

}
