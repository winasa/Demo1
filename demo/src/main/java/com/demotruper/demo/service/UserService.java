package com.demotruper.demo.service;

import com.demotruper.demo.dto.UserDTO;
import com.demotruper.demo.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getUsers();

    Optional<UserEntity> getUsersById(Integer id);

    UserDTO updateUserEntity(UserDTO userDTO);

    void deleteUserById(Integer id);

    UserEntity saveUser(UserDTO userDTO);
}
