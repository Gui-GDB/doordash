package com.gdb.doordash.handler;

import com.gdb.doordash.constant.MessageConstant;
import com.gdb.doordash.exception.BaseException;
import com.gdb.doordash.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     */
    @ExceptionHandler
    public Result<String> exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    /**
     * 处理添加用户重名的问题
     */
    @ExceptionHandler()
    public Result<String> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        String message = e.getMessage();
        if(message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}