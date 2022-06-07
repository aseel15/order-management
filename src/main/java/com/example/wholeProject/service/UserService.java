package com.example.wholeProject.service;

import com.example.wholeProject.dto.UserDto;
import com.example.wholeProject.entities.User;

import java.util.List;

public interface UserService {

   /* List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    UserDto addUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, int id);

    void deleteUser(int id);*/

    UserDto getByUsername(String username);
}
