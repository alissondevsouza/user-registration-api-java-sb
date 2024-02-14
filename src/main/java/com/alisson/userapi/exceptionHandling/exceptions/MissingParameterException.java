package com.alisson.userapi.exceptionHandling.exceptions;

public class MissingParameterException extends RuntimeException{

    public MissingParameterException(String message) {
        super(message);
    }
}
