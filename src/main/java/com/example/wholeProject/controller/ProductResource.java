package com.example.wholeProject.controller;


import com.example.wholeProject.dto.ProductDto;
import com.example.wholeProject.exception.BadRequestException;
import com.example.wholeProject.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(value = "operations for product controller")
@RestController
@RequestMapping("/api/products")
public class ProductResource {
    @Autowired
    ProductService productService;

    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @ApiOperation(value = "Get all the products REST API")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @ApiOperation(value = "Get a product by id REST API")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    /*@GetMapping("/requestParam")
    public ResponseEntity<ProductDto> getProductById2(@RequestParam(name = "id") int id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }*/

    @ApiOperation(value = "Create a new product REST API")
    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto){
        if (productDto.getId() != null) {
            //log.error("Cannot have an ID {}", productDto);
            throw new BadRequestException(ProductResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(productService.addProduct(productDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update exist product by id REST API")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable(name = "id") int id){
        return new ResponseEntity<>(productService.updateProduct(productDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete exist product by id REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("successfully deleted!", HttpStatus.OK);
    }

    @ApiOperation(value = "Get all the products that are stock able REST API")
    @GetMapping("/stockAble")
    public ResponseEntity<List<ProductDto>> getAllStockAbleProducts(){
        return ResponseEntity.ok().body(productService.getAllStockAbleProducts());
    }

    @ApiOperation(value = "Get all the products that are less than certain price REST API")
    @GetMapping("/price/{price}")
    public ResponseEntity<List<String>> getProductsLessThanPrice(@PathVariable double price){
        return ResponseEntity.ok().body(productService.getAllProductsLessThanPrice(price));
    }
}

