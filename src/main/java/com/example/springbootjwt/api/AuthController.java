package com.example.springbootjwt.api;

import com.example.springbootjwt.core.business.auth.TokenManager;
import com.example.springbootjwt.entities.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    TokenManager tokenManager;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));

            return ResponseEntity.ok(tokenManager.generateToken(userLogin.getUsername()));
        } catch (Exception e) {
            throw e;
        }
    }
}
