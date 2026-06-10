package com.cinfly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartDto {
    private Long bookId;
    private Long userId;
    private String  bookName;
    private BigDecimal price;
    private String image;
}
