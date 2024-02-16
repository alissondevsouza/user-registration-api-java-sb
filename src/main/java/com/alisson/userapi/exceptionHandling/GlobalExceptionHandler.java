package com.alisson.userapi.exceptionHandling;

import com.alisson.userapi.exceptionHandling.exceptions.LoginNotFoundException;
import com.alisson.userapi.exceptionHandling.exceptions.MissingParameterException;
import com.alisson.userapi.exceptionHandling.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ResponseExceptionMessage> runtimeErrorHandler(RuntimeException exception) {

        ResponseExceptionMessage responseExceptionMessage =
                new ResponseExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseExceptionMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ResponseExceptionMessage> userNotFoundErrorHandler(UserNotFoundException exception) {

        ResponseExceptionMessage responseExceptionMessage =
                new ResponseExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseExceptionMessage);
    }

    @ExceptionHandler(MissingParameterException.class)
    private ResponseEntity<ResponseExceptionMessage> userNotFoundErrorHandler(MissingParameterException exception) {

        ResponseExceptionMessage responseExceptionMessage =
                new ResponseExceptionMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseExceptionMessage);
    }

    @ExceptionHandler(LoginNotFoundException.class)
    private ResponseEntity<ResponseExceptionMessage> loginNotFoundErrorHandler(LoginNotFoundException exception) {

        ResponseExceptionMessage responseExceptionMessage =
                new ResponseExceptionMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseExceptionMessage);
    }



}
