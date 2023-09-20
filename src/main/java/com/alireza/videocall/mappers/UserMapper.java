package com.alireza.videocall.mappers;


import com.alireza.videocall.dto.user.RegisterRequest;
import com.alireza.videocall.dto.user.UserResponse;
import com.alireza.videocall.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    User userDtoToUser(RegisterRequest request);

    UserResponse userToUserDto(User user);
    List<UserResponse> userListToDtoList(List<User> users);
}
