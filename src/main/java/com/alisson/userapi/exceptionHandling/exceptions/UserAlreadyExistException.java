package com.alisson.userapi.exceptionHandling.exceptions;

public class UserAlreadyExistException extends RuntimeException{
        public UserAlreadyExistException(String message) {
            super(message);
        }
}
