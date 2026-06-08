package com.cinfly.exceptionhandler;

import com.cinfly.constant.Result;
import com.cinfly.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@Slf4j
public class exceptionhandler {
    @ExceptionHandler
    public Result error(BusinessException e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

}
