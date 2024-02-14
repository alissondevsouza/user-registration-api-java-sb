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
    private ResponseEntity<ResponseErrorMessage> runtimeErrorHandler(RuntimeException exception) {

        ResponseErrorMessage responseErrorMessage =
                new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ResponseErrorMessage> userNotFoundErrorHandler(UserNotFoundException exception) {

        ResponseErrorMessage responseErrorMessage =
                new ResponseErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorMessage);
    }

    @ExceptionHandler(MissingParameterException.class)
    private ResponseEntity<ResponseErrorMessage> userNotFoundErrorHandler(MissingParameterException exception) {

        ResponseErrorMessage responseErrorMessage =
                new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorMessage);
    }

    @ExceptionHandler(LoginNotFoundException.class)
    private ResponseEntity<ResponseErrorMessage> loginNotFoundErrorHandler(LoginNotFoundException exception) {

        ResponseErrorMessage responseErrorMessage =
                new ResponseErrorMessage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseErrorMessage);
    }



}
