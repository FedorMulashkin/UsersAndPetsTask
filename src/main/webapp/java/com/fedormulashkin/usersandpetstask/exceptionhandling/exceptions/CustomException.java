package com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Родительский абстрактный класс для исключений при выполнении возможных запросов
 */
public abstract class CustomException extends RuntimeException {
    private int id;
    private String message;
    private HttpStatus httpStatus;

    public CustomException(int id, String message, HttpStatus httpStatus) {
        this.id = id;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
