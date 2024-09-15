package com.dino.userauthentication.services;

import com.dino.userauthentication.exceptions.UserAlreadyExistException;
import com.dino.userauthentication.models.Status;
import com.dino.userauthentication.models.User;
import com.dino.userauthentication.repositories.UserAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService implements IAuthService{

    @Autowired
    private UserAuthRepo userAuthRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User signup(String email,String name,String password) throws UserAlreadyExistException {
        Optional<User> user = userAuthRepo.findUserByEmail(email);
        if(user.isPresent()){
            throw new UserAlreadyExistException("User Already Exist");
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser.setStatus(Status.ACTIVE);
        userAuthRepo.save(newUser);
        return newUser;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public User logout(String email) {
        return null;
    }
}
