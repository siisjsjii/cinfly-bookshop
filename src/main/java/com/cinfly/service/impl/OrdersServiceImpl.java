package com.cinfly.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cinfly.constant.PageResult;
import com.cinfly.dto.CartItemsDto;
import com.cinfly.dto.OrderDto;
import com.cinfly.dto.OrderPageQueryDto;
import com.cinfly.entity.Books;
import com.cinfly.entity.OrderInfo;
import com.cinfly.entity.Orders;
import com.cinfly.exception.BusinessException;
import com.cinfly.mapper.OrdersMapper;
import com.cinfly.service.IBooksService;
import com.cinfly.service.IOrderInfoService;
import com.cinfly.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cinfly 插入订单表和订单明细表,需要进行事务管理
 * @since 2026-06-10
 */
@Transactional
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private IBooksService booksService;
    @Override
    public Orders generateOrder(OrderDto orderDto) {

        Orders orders = new Orders();
        orders.setUserId(orderDto.getUserId());
        orders.setOrderNo(String.valueOf(System.currentTimeMillis()));
        orders.setStatus(0);
        BigDecimal count=BigDecimal.ZERO;
        StringBuilder stringBuilder = new StringBuilder();
        for (CartItemsDto cartItem : orderDto.getCartItems()) {
            count=count.add(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            stringBuilder.append(cartItem.getBookName()+":"+cartItem.getQuantity()+"本").append(",");
        }
        orders.setTotalAmount(count);
        orders.setProductNames(stringBuilder.toString());
        orders.setCreateTime(LocalDateTime.now());
        ordersMapper.insert( orders);//mp自动返回主键
        orders.setId(orders.getId());
        //返回了orderid,插入到订单明细表
        for (CartItemsDto cartItem : orderDto.getCartItems()) {
            OrderInfo orderInfo = new OrderInfo();
            BeanUtils.copyProperties(cartItem,orderInfo);
            orderInfo.setOrderId(orders.getId());
            orderInfoService.save(orderInfo);
        }
        return orders;
    }

    @Override
    public void submit(Long orderId) {
        Orders order = getById(orderId);
        List<OrderInfo> list = orderInfoService.lambdaQuery().eq(OrderInfo::getOrderId, orderId).list();
        for (OrderInfo orderInfo : list) {
            //根据bookId查询到书籍的数量,用书籍数量减去orderinfo的数量,剪完后根据剩余数量决定更新
            Long bookId = orderInfo.getBookId();
            Books book = booksService.getById(bookId);
            Integer originStock = book.getStock();
            Integer remainStock=originStock-orderInfo.getQuantity();
            Long orginSalesCount = book.getSalesCount();
            //如果库存算出小于零直接抛异常返回业务异常xxx书籍库存不足
            if(remainStock<0)
            {
                throw new BusinessException(orderInfo.getBookName()+"库存不足");
            }
            else if(remainStock==0)
            {
                //书籍的状态设置为售罄
                booksService.lambdaUpdate().eq(Books::getId, bookId).set(Books::getStatus, 2).update();

            }
            //剩余数量大于0直接减库存更新销量,更新时间
            //减库存
            booksService.lambdaUpdate().eq(Books::getId, bookId).set(Books::getStock, remainStock).update();
            //更新销量,更新时间
            booksService.lambdaUpdate().eq(Books::getId, bookId).set(Books::getSalesCount,orginSalesCount+orderInfo.getQuantity()).update();
            booksService.lambdaUpdate().eq(Books::getId, bookId).set(Books::getUpdateTime, LocalDateTime.now()).update();
        }
        order.setStatus(1);
        updateById( order);
    }

    @Override
    public PageResult listAll(OrderPageQueryDto orderPageQueryDto) {
        Page<Orders> page = new Page<>(orderPageQueryDto.getPage(), orderPageQueryDto.getSize());
        String orderNo= orderPageQueryDto.getOrderNo();
        Integer status= orderPageQueryDto.getStatus();
        Long userId= orderPageQueryDto.getUserId();
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<Orders>().like(orderNo != null, Orders::getOrderNo, orderNo)
                .eq(status != null, Orders::getStatus, status)
                .eq(Orders::getUserId, userId)
                .orderByDesc(Orders::getCreateTime);
        Page<Orders> res = this.page(page, wrapper);
        return new PageResult(res.getTotal(),res.getRecords());

    }
}
