package com.example.reservationsystem.userapi;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userToEntity(User user);

    User userEntityToUser(UserEntity userEntity);

    List<User> userEntityListToUserList(List<UserEntity> userEntityList);

}
