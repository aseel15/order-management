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

@Api(value = "User controller provide CRUD operations on the users")
@RestController
@RequestMapping("/api/users")
public class UserResource {
    @Autowired
    UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

   /* @ApiOperation(value = "Get All Users REST API")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @ApiOperation(value = "Get the user By id REST API")
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }*/


    @ApiOperation(value = "add new user REST API")
    @PostMapping ("/signup")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
        if (userDto.getId() != null) {
            //log.error("Cannot have an ID {}", userDto);
            throw new BadRequestException(UserResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

   /* @ApiOperation(value = "update the data about the user REST API")
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(userService.updateUser(userDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "delete the user REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("successfully deleted!", HttpStatus.OK);

    }*/
}
