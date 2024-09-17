package com.dino.userauthentication.services;

import com.dino.userauthentication.exceptions.InvalidCredentialException;
import com.dino.userauthentication.exceptions.UserAlreadyExistException;
import com.dino.userauthentication.models.Status;
import com.dino.userauthentication.models.User;
import com.dino.userauthentication.repositories.UserAuthRepo;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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
    public Pair<User,MultiValueMap<String,String>> login(String email, String password) throws InvalidCredentialException {
        Optional<User> optionalUser = userAuthRepo.findUserByEmail(email);
        if(optionalUser.isPresent()){
            User user =  optionalUser.get();
            if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
                throw new InvalidCredentialException("Please put valid credential");
            }
//            String message = "{\n" +
//                "   \"email\": \"anurag@scaler.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"buddy\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"25thSept2024\"\n" +
//                "}";

            Map<String,Object> claims = new HashMap<>();
            claims.put("user_id : ",user.getId());
            claims.put("user_name : ",user.getName());
            claims.put("user_email : ",user.getEmail());
            claims.put("roles : ",user.getRoles());
            long timeInMillis = System.currentTimeMillis();
            claims.put("iat",timeInMillis);
            claims.put("exp",timeInMillis+86400000);
            claims.put("iss","Dino Chakraborty");


            MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
            SecretKey secretKey = macAlgorithm.key().build();

//            byte[] content = message.getBytes(StandardCharsets.UTF_8);
//            String token = Jwts.builder().content(content).signWith(secretKey).compact();
            String token = Jwts.builder().claims(claims).signWith(secretKey).compact();
            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.SET_COOKIE,token);
            Pair<User,MultiValueMap<String,String>> pair = new Pair<>(user,headers);
            return pair;
        }

        return null;
    }

    @Override
    public User logout(String email) {
        return null;
    }
}
