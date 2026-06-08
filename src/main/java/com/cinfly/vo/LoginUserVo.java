package com.cinfly.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo {
    private Integer id;
    private String username;
    private String token;
}
