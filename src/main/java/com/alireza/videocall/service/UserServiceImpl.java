package com.alireza.videocall.service;


import com.alireza.videocall.dto.user.LoginRequest;
import com.alireza.videocall.dto.user.LogoutRequest;
import com.alireza.videocall.dto.user.RegisterRequest;
import com.alireza.videocall.dto.user.UserResponse;
import com.alireza.videocall.exception.UserExistException;
import com.alireza.videocall.exception.UserNotFoundException;
import com.alireza.videocall.mappers.UserMapperImpl;
import com.alireza.videocall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapperImpl userMapper;

    @Override
    public void register(RegisterRequest request) {
        var user = userMapper.userDtoToUser(request);
        user.setStatus("online");
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserExistException(String.format("an user with this email: %s already registered", user.getEmail()));
        }
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new UserNotFoundException("no user found with this email: " + loginRequest.getEmail())
        );
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new UserNotFoundException("password incorrect");
        }
        user.setStatus("online");
        userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        var user = userRepository.findByEmail(logoutRequest.getEmail()).orElseThrow(
                () -> new UserNotFoundException("no user found with this email: " + logoutRequest.getEmail())
        );
        user.setStatus("offline");
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> findAll() {
        return userMapper.userListToDtoList(userRepository.findAll());
    }
}
