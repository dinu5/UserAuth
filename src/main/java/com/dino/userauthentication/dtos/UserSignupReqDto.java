package com.dino.userauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupReqDto {
    private String name;
    private String email;
    private String password;
}
