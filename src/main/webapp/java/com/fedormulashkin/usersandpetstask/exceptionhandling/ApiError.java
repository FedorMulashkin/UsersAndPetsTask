package com.fedormulashkin.usersandpetstask.exceptionhandling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

class ApiError {
    int exceptionId;
    private HttpStatus status;
    private String message;
    @JsonIgnore
    private String debugMessage;

    ApiError(int exceptionId, HttpStatus status) {
        this.exceptionId = exceptionId;
        this.status = status;
    }

    ApiError(int exceptionId, HttpStatus status, Throwable ex) {
        this.exceptionId = exceptionId;
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(int exceptionId, HttpStatus status, String message, Throwable ex) {
        this.exceptionId = exceptionId;
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public int getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(int exceptionId) {
        this.exceptionId = exceptionId;
    }
}