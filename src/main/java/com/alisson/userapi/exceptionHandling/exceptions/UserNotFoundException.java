package com.alisson.userapi.exceptionHandling.exceptions;

public class UserNotFoundException extends RuntimeException{
        public UserNotFoundException(String message) {
            super(message);
        }
}
