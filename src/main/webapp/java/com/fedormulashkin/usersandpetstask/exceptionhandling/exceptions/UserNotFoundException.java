package com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions;

public class UserNotFoundException extends CustomNotFoundException{
    public UserNotFoundException(String message) {
        super(3, message);
    }
}
