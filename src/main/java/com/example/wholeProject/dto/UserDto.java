package com.example.wholeProject.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Api(value = "the user data")
public class UserDto {
    @ApiModelProperty(value = "the id of the user")
    private Integer id;

    @ApiModelProperty(value = "the name of the user")
    private String name;

    @ApiModelProperty(value = "the username of the user")
    private String username;

    @ApiModelProperty(value = "the email of the user")
    private String email;

    @ApiModelProperty(value = "the password of the user")
    private String password;
}
