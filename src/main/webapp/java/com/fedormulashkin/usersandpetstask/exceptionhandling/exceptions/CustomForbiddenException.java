package com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomForbiddenException extends CustomException{
    public CustomForbiddenException(int id, String message) {
        super(id, message, FORBIDDEN);
    }
}
