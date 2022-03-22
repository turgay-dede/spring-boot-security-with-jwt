package com.example.springbootjwt.api.response;

import lombok.Data;

@Data
public class AuthResponse {
    int userId;
    String accessToken;
}
