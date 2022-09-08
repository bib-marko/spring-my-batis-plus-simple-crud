package com.demomybatisplus.mybatisplus.exception;

public class ListEmptyException extends RuntimeException{
    public ListEmptyException(String message) {
        super(message);
    }
}
