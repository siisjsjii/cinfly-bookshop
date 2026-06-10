package com.cinfly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
  /*  userId: number
    cartItems: Array<{
        bookId: number
        bookName: string
        price: number
        quantity: number
    }>*/
    private Long userId;
    private List<CartItemsDto> cartItems;
}
