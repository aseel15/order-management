package com.example.wholeProject.repositries;

import com.example.wholeProject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//indicate that the class provides the mechanism for retrieve, update, delete,...etc on objects
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product_tbl p WHERE p.stock_able = true", nativeQuery = true)
    List<Product> listAllStockAbleProducts();

    @Query(value = "SELECT p.name FROM product_tbl p WHERE p.price <= :price", nativeQuery = true)
    List<String> listAllProductsLessThanPrice(double price);

}
