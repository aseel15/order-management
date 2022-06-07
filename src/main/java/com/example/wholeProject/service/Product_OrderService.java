package com.example.wholeProject.service;

import com.example.wholeProject.dto.Product_OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Product_OrderService {

    List<Product_OrderDto> getOrderProductByPrice(double price);

    List<Product_OrderDto> getOrderContainsProduct(String name);
}
