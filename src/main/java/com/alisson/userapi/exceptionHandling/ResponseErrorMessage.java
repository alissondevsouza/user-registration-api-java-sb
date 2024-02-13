package com.alisson.userapi.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ResponseErrorMessage {

    private HttpStatus status;
    private String data;
}
