package com.example.wholeProject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data

//DTO(Data Transfer Object) to indicate that this class carries data between processes
public class ProductDto {
    @ApiModelProperty(value = "Product id")
    private Integer id;

    @ApiModelProperty(value = "The slug of the product")
    private String slug;

    @ApiModelProperty(value = "The name of the product")
    private String name;

    @ApiModelProperty(value = "The reference of the product")

    private String reference;

    @ApiModelProperty(value = "The price of the product")
    private double price;

    @ApiModelProperty(value = "The vat of the price")
    private double vat;

    @ApiModelProperty(value = "Check if the product is stock able or not")
    private boolean stockAble;
}
