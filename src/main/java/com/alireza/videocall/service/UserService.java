package com.alireza.videocall.service;

import com.alireza.videocall.dto.user.LoginRequest;
import com.alireza.videocall.dto.user.LogoutRequest;
import com.alireza.videocall.dto.user.RegisterRequest;
import com.alireza.videocall.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    void register(RegisterRequest request);
    UserResponse login(LoginRequest loginRequest);

    void logout(LogoutRequest logoutRequest);
    List<UserResponse> findAll();
}
