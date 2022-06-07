package com.example.wholeProject.service;

import com.example.wholeProject.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer id);

    ProductDto addProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, Integer id);

    void deleteProduct(Integer id);

    List<ProductDto> getAllStockAbleProducts();

    List<String> getAllProductsLessThanPrice(double price);
}
