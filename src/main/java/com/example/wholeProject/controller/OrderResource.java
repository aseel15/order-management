package com.example.wholeProject.controller;


import com.example.wholeProject.dto.OrderDto;
import com.example.wholeProject.exception.BadRequestException;
import com.example.wholeProject.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "order controller to manage the orders")
@RestController
@RequestMapping("/api/orders")
public class OrderResource {
    @Autowired //inject the orderService to the controller class
    OrderService orderService;

    public OrderResource(OrderService orderService){
        this.orderService = orderService;
    }
    @ApiOperation(value = "Get all the orders REST API")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @ApiOperation(value = "Get the order by Id REST API")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    /*@GetMapping("/requestParam")
    public ResponseEntity<OrderDto> getOrderById2(@RequestParam(name = "id") int id){
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }*/

    @ApiOperation(value = "Create an order REST API")
    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@Valid @RequestBody OrderDto orderDto){
        if (orderDto.getId() != null) {
            //log.error("Cannot have an ID {}", orderDto);
            throw new BadRequestException(OrderResource.class.getSimpleName(),
                    "id");
        }
        return new ResponseEntity<>(orderService.addOrder(orderDto), HttpStatus.CREATED);
    }

    /*@ApiOperation(value = "update the order by id REST API")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable(name = "id") int id){
        return new ResponseEntity<>(orderService.updateOrder(orderDto, id), HttpStatus.OK);
    }*/
    @ApiOperation(value = "Delete the order by Id REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>("successfully deleted!", HttpStatus.OK);
    }

    @ApiOperation(value = "Get the orders by customer id REST API")
    @GetMapping("/customerId/{customer_id}")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable(name = "customer_id") Integer customer_id){
        return ResponseEntity.ok().body(orderService.getOrdersByCustomerId(customer_id));
    }

    @ApiOperation(value = "Get all the orders today REST API")
    @GetMapping("/ordersToday")
    public ResponseEntity<List<OrderDto>> getOrdersToday(){
        return ResponseEntity.ok().body(orderService.getOrdersToday());
    }
}
