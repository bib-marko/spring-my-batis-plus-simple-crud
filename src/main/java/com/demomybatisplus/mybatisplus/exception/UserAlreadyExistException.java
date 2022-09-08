package com.demomybatisplus.mybatisplus.exception;

    public class UserAlreadyExistException extends RuntimeException{
        public UserAlreadyExistException(String message) {
            super(message);
        }
    }
