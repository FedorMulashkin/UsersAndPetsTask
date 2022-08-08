package com.fedormulashkin.usersandpetstask.exceptionhandling;

import org.springframework.http.HttpStatus;

public class CustomError {
    private final HttpStatus httpStatus;
    private final String message;

    public CustomError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}