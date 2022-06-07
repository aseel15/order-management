package com.example.wholeProject.controller;

import com.example.wholeProject.dto.CustomerDto;
import com.example.wholeProject.exception.BadRequestException;
import com.example.wholeProject.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Customer controller provide data about the customer")
@RestController
@RequestMapping("/api/customers")
public class CustomerResource {
    @Autowired
    CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Get All Customers REST API")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @ApiOperation(value = "Get the customer By id REST API")
    @GetMapping("/id/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }


    @ApiOperation(value = "add new customer REST API")
    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
        if (customerDto.getId() != null) {
            //log.error("Cannot have an ID {}", customerDto);
            throw new BadRequestException(CustomerResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update the data about the customer REST API")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto, @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }

    @ApiOperation(value = "delete the customer REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("successfully deleted!", HttpStatus.OK);

    }

    @ApiOperation(value = "get the customer by first name and order them according to their last names REST API")
    @GetMapping("/firstname/{first_name}")
    public ResponseEntity<List<CustomerDto>> getCustomersByFirstNameOrderByLastName(@PathVariable(name = "first_name") String first_name){
        return ResponseEntity.ok().body(customerService.getCustomerByFirstNameOrderedByLastName(first_name));
    }

    @ApiOperation(value = "get the customers that ordered a product over certain price REST API")
    @GetMapping("/price/{price}")
    public ResponseEntity<List<CustomerDto>> getCustomersOrderedOverPrice(@PathVariable(name = "price")double price){
        return ResponseEntity.ok().body(customerService.getCustomersOrderedOverPrice(price));
    }

}
