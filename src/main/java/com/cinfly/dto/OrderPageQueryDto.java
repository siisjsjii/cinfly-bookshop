package com.cinfly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPageQueryDto {
  /*  page?: number
    size?: number
    orderNo?: string
    status?: number | null
    userId?: number*/
    private Integer page;
    private Integer size;
    private String orderNo;
    private Integer status;
    private Long userId;

}
