package com.example.wholeProject.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Api(value = "the data needed to log in the system")
public class LoginDto {
    @ApiModelProperty(value = "the username of the user")
    private String username;

    @ApiModelProperty(value = "the password of the user")
    private String password;
}
