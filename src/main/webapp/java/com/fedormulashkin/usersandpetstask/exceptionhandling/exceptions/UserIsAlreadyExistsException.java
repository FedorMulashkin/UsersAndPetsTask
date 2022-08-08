package com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions;

public class UserIsAlreadyExistsException extends CustomForbiddenException{
    public UserIsAlreadyExistsException( String message) {
        super(2, message);
    }
}
