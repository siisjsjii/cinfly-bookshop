
package com.cinfly.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String code;

    public BusinessException(String message) {
        super(message);
        this.code = "0";
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
}
