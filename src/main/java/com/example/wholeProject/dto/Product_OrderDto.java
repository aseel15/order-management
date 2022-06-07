package com.example.wholeProject.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Api(value = "the product_order model information")
//DTO(Data Transfer Object) to indicate that this class carries data between processes
public class Product_OrderDto {

    @ApiModelProperty(value = "Product of the order")
    //@NotEmpty(message = "Product should not be null or empty")
    private ProductDto productDto;

    @ApiModelProperty(value = "Order of the product")
    @NotEmpty(message = "Order should not be null or empty")
    private OrderDto orderDto;

    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private int quantity;

    @ApiModelProperty(value = "The price of the product order")
    @NotEmpty(message = "price should not be null or empty")
    private double price;

    @ApiModelProperty(value = "The vat of the product order")
    @NotEmpty(message = "Vat should not be null or empty")
    private double vat;
}
