package com.cinfly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemsDto {
    private Long bookId;
    private String bookName;
    private BigDecimal price;
    private Integer quantity;
}
