package com.example.wholeProject.controller;

import com.example.wholeProject.dto.UserDto;
import com.example.wholeProject.exception.BadRequestException;
import com.example.wholeProject.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "User controller signup to api")
@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "add new user REST API")
    @PostMapping ("/signup")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        if (userDto.getId() != null) { //do not enter the id it is auto generated
            throw new BadRequestException(UserResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

}
