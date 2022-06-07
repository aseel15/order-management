package com.example.wholeProject.service.serviceImp;

import com.example.wholeProject.dto.ProductDto;
import com.example.wholeProject.entities.Product;
import com.example.wholeProject.exception.ResourceNotFoundException;
import com.example.wholeProject.repositries.ProductRepository;
import com.example.wholeProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductServiceImp (ProductRepository productRepository){this.productRepository = productRepository;}

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());

    }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ProductServiceImp.class.getName(), "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);
        return mapToDto(newProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ProductServiceImp.class.getName(), "id", id));

        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setStockAble(productDto.isStockAble());
        product.setVat(productDto.getVat());

        Product updatedProduct = productRepository.save(product);
        ProductDto updatedProductDto = mapToDto(updatedProduct);
        return updatedProductDto;
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ProductServiceImp.class.getName(), "id", id));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> getAllStockAbleProducts() {
        List<Product> products = productRepository.listAllStockAbleProducts();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllProductsLessThanPrice(double price) {
        List<String> products = productRepository.listAllProductsLessThanPrice(price);
        return products;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();

        //product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setReference(productDto.getReference());
        product.setSlug(productDto.getSlug());
        product.setStockAble(productDto.isStockAble());
        product.setVat(productDto.getVat());

        return product;
    }

    private ProductDto mapToDto(Product product) {
        //ProductDto productDto = new ProductDto(product.getId(), product.getCustomer(), product.getProductedAt());
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setSlug(product.getSlug());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setVat(product.getVat());
        productDto.setStockAble(product.isStockAble());
        productDto.setReference(product.getReference());

        return productDto;

    }
}
