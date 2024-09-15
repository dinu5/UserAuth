package com.dino.userauthentication.exceptions;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
