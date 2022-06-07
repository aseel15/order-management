package com.example.wholeProject.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

//DTO(Data Transfer Object) to indicate that this class carries data between processes
@Api(value = "customer model information")
public class CustomerDto {

    @ApiModelProperty(value = "id customer")
    private Integer id;

    @ApiModelProperty(value = "First Name of The Customer")
    //@NotEmpty(message = "first name should not be null or empty")
    @NonNull
    private String firstName;

    @ApiModelProperty(value = "Last name of the customer")
    //@NotEmpty(message = "last name should not be null or empty")
    private String lastName;

    @ApiModelProperty(value = "The date of birth of the customer")
    private LocalDate bornAt;

}
