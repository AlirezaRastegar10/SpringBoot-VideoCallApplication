package com.alireza.videocall.controller;


import com.alireza.videocall.dto.user.LoginRequest;
import com.alireza.videocall.dto.user.LogoutRequest;
import com.alireza.videocall.dto.user.RegisterRequest;
import com.alireza.videocall.dto.user.UserResponse;
import com.alireza.videocall.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        userService.logout(logoutRequest);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
}
