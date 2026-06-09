package com.cinfly.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "成功", data);
    }
    
    public static <T> Result<T> error(String message) {
        return new Result<>(0, message, null);
    }
    //空参构造success
    public static <T> Result<T> success() {
        return new Result<>(1, "成功", null);
    }
}
