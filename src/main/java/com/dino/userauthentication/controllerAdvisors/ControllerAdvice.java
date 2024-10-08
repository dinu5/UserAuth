package com.dino.userauthentication.controllerAdvisors;

import com.dino.userauthentication.exceptions.InvalidCredentialException;
import com.dino.userauthentication.exceptions.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler({UserAlreadyExistException.class, InvalidCredentialException.class})
    public ResponseEntity<String> handleExceptionForInvalidUser(Exception ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
