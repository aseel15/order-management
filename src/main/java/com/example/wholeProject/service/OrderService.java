package com.example.wholeProject.service;

import com.example.wholeProject.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    
     List<OrderDto> getAllOrders();

     OrderDto getOrderById(Integer id);

     OrderDto addOrder(OrderDto orderDto);

     OrderDto updateOrder(OrderDto orderDto, Integer id);

     void deleteOrder(Integer id);

     List<OrderDto> getOrdersByCustomerId(Integer id);

     List<OrderDto> getOrdersToday();
}
