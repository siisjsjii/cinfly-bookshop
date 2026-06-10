package com.cinfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cinfly.dto.AddCartDto;
import com.cinfly.entity.Books;
import com.cinfly.entity.Cart;
import com.cinfly.mapper.CartMapper;
import com.cinfly.service.IBooksService;
import com.cinfly.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author cinfly
 * @since 2026-06-10
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private IBooksService booksService;
    @Override
    public void saveCart(AddCartDto addCartDto) {
        Cart cart = new Cart();
        cart.setUserId(addCartDto.getUserId());
        cart.setBookId(addCartDto.getBookId());
        cart.setBookName(addCartDto.getBookName());
        cart.setPrice(addCartDto.getPrice());
        cart.setImage(addCartDto.getImage());
        cart.setUserId(addCartDto.getUserId());
        cart.setSelected(0);
        //根据用户id和bookid查询,如果存在则更新数量,不存在则新增
        Cart cart1 = getOne(new LambdaQueryWrapper<Cart>().eq(Cart::getUserId, cart.getUserId()).eq(Cart::getBookId, cart.getBookId()));
        if(cart1!=null)
        {
            cart1.setQuantity(cart1.getQuantity()+1);
            cart1.setUpdateTime(LocalDateTime.now());
            updateById(cart1);
        }
        else
        {
            cart.setQuantity(1);
            cart.setCreateTime(LocalDateTime.now());
            cart.setUpdateTime(LocalDateTime.now());
            save(cart);
        }
        //修改书籍的收藏数量
        Books book = booksService.getById(addCartDto.getBookId());
        book.setFavoriteCount(book.getFavoriteCount()+1L);
        booksService.updateById(book);
    }
}
