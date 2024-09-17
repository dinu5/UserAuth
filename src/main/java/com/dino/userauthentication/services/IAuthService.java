package com.dino.userauthentication.services;

import com.dino.userauthentication.exceptions.InvalidCredentialException;
import com.dino.userauthentication.exceptions.UserAlreadyExistException;
import com.dino.userauthentication.models.User;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public interface IAuthService {
    public User signup(String email,String name,String password) throws UserAlreadyExistException;
    public Pair<User, MultiValueMap<String,String>> login(String email, String password) throws InvalidCredentialException;
    public User logout(String email);
}
