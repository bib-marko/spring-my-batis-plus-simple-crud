package com.demomybatisplus.mybatisplus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity exception(UserAlreadyExistException exception){
        return new ResponseEntity(exception.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity exception(UserNotExistException exception){
        return new ResponseEntity(exception.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
