package com.dino.userauthentication.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReqDto {
    private String email;
    private String password;
}
