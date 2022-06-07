package com.example.wholeProject.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

//create constructor of this class with all arguments
@AllArgsConstructor
//create constructor of this class without arguments
@NoArgsConstructor
//create setter & getters and toString method of the data fields of this class
@Data

@Api(value = "The stock model information")
//DTO(Data Transfer Object) to indicate that this class carries data between processes
public class StockDto {

    @NonNull
    @ApiModelProperty(value = "Stock id")
    private Integer id;

    @NonNull
    @ApiModelProperty(value = "The quantity of the product in the stock")
    //@NotEmpty(message = "quantity should not be null or empty")
    private Integer quantity;

    @ApiModelProperty(value = "The product info in this stock")
    //@NonNull
    //@NotEmpty(message = "product should not be null or empty")
    @Transient
    private ProductDto productDto;

    @ApiModelProperty(value = "The last updated date of this stock")
    private Date updatedAt;
}
