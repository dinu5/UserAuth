package com.dino.userauthentication.exceptions;

public class InvalidCredentialException extends Exception{
    public InvalidCredentialException(String message){
        super(message);
    }
}
