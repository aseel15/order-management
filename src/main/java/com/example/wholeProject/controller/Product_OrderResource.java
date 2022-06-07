package com.example.wholeProject.controller;


import com.example.wholeProject.dto.Product_OrderDto;
import com.example.wholeProject.service.Product_OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(value = "product_order resource")
@RestController
@RequestMapping("/api/product_order")
public class Product_OrderResource {

    Product_OrderService product_orderService;

    @Autowired
    public Product_OrderResource (Product_OrderService product_orderService){this.product_orderService = product_orderService;}


    @ApiOperation(value = "Get all the product_orders by certain price REST API")
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Product_OrderDto>> getProductOrderByPrice(@PathVariable(name = "price") double price){
        return ResponseEntity.ok().body(product_orderService.getOrderProductByPrice(price));
    }

    //Get the products that ordered by product's name
    @ApiOperation(value = "Get all the product_orders by certain product name REST API")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product_OrderDto>> getProductOrderByName(@PathVariable(name = "name") String name){
        return ResponseEntity.ok().body(product_orderService.getOrderContainsProduct(name));
    }

}
