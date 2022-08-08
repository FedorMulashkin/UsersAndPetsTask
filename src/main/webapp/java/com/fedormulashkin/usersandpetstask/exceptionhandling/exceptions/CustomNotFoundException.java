package com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public abstract class CustomNotFoundException extends CustomException{
    public CustomNotFoundException(int id, String message) {
        super(id, message, NOT_FOUND);
    }


}
