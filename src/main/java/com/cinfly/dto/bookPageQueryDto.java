package com.cinfly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class bookPageQueryDto {
    private Integer page;
    private Integer size;
    private String name;
    private String author;
    private Integer categoryId;
    private Integer status;
}
