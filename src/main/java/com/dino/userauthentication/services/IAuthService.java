package com.dino.userauthentication.services;

import com.dino.userauthentication.exceptions.UserAlreadyExistException;
import com.dino.userauthentication.models.User;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    public User signup(String email,String name,String password) throws UserAlreadyExistException;
    public User login(String email,String password);
    public User logout(String email);
}
