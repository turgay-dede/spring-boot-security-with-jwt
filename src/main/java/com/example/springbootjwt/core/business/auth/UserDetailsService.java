package com.example.springbootjwt.core.business.auth;

import com.example.springbootjwt.business.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserService userService;

    public UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userService.findByUsername(username).isPresent()) {
            return new User(username, userService.findByUsername(username).get().getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException(username);
    }
}
