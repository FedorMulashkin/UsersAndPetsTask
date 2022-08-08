package com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions;

public class PetNotFoundException extends CustomNotFoundException{
    public PetNotFoundException( String message) {
        super(1, message);
    }
}
