package com.dino.userauthentication.util;

import com.dino.userauthentication.dtos.UserSignupResDto;
import com.dino.userauthentication.models.User;
import org.springframework.stereotype.Component;

@Component
public class Utility {
    public UserSignupResDto convertToUserSignupResDto(User user) {
        UserSignupResDto userSignupResDto = new UserSignupResDto();
        userSignupResDto.setEmail(user.getEmail());
        userSignupResDto.setName(user.getName());
        return userSignupResDto;
    }
}
