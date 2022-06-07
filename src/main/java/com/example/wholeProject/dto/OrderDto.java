package com.example.wholeProject.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
//DTO(Data Transfer Object) to indicate that this class carries data between processes
@Api(value = "order model information")
public class OrderDto {
    @ApiModelProperty(value = "order id")
    private Integer id;

    @ApiModelProperty(value = "the customer object to get his id")
    //@NotEmpty(message = "customer object should not be null or empty")
    private CustomerDto customerDto;

    @ApiModelProperty(value = "the date of the order")
    //@NotEmpty(message = "date should not be null or empty")
    private Date orderedAt;
}
