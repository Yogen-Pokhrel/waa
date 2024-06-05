package com.waa.auth.service;


import com.waa.auth.dto.LoginRequest;
import com.waa.auth.dto.LoginResponse;
import com.waa.auth.dto.RefreshTokenRequest;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
