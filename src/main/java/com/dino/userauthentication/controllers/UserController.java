package com.dino.userauthentication.controllers;

import com.dino.userauthentication.dtos.UserSignupReqDto;
import com.dino.userauthentication.dtos.UserSignupResDto;
import com.dino.userauthentication.exceptions.UserAlreadyExistException;
import com.dino.userauthentication.models.User;
import com.dino.userauthentication.services.IAuthService;
import com.dino.userauthentication.util.Utility;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Utility utility;
    @Autowired
    private IAuthService iAuthService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignupResDto> signup(@RequestBody UserSignupReqDto userSignupReqDto) throws UserAlreadyExistException {
        String email = userSignupReqDto.getEmail();
        String password = userSignupReqDto.getPassword();
        String name = userSignupReqDto.getName();
        if(email == null || password == null || name == null){
            throw new IllegalIdentifierException("please fill all the required fields");
        }
        User user = iAuthService.signup(email,name,password);
        UserSignupResDto userSignupResDto = utility.convertToUserSignupResDto(user);
        return new ResponseEntity<>(userSignupResDto, HttpStatus.CREATED);
    }



}
