package com.alisson.userapi.exceptionHandling.exceptions;

public class LoginNotFoundException extends RuntimeException{

    public LoginNotFoundException(String message) {
        super(message);
    }
}
