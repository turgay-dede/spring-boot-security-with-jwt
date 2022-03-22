package com.example.springbootjwt.api;

import com.example.springbootjwt.api.response.AuthResponse;
import com.example.springbootjwt.business.UserService;
import com.example.springbootjwt.core.business.auth.TokenManager;
import com.example.springbootjwt.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final TokenManager tokenManager;

    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(authenticate(user));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        // Username var ise BAD_REQUEST
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userService.add(user);

        return new ResponseEntity<>(authenticate(user), HttpStatus.CREATED);
    }

    private AuthResponse authenticate(User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            //SecurityContextHolder.getContext().setAuthentication(auth);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(tokenManager.generateToken(user.getUsername()));
            authResponse.setUserId(userService.findByUsername(user.getUsername()).get().getId());
            return authResponse;
        } catch (Exception e) {
            throw e;
        }
    }
}
