package com.example.wholeProject.repositries;

import com.example.wholeProject.entities.Product_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//indicate that the class provides the mechanism for retrieve, update, delete,...etc on objects
@Repository
public interface Product_OrderRepository extends JpaRepository<Product_Order, Integer> {

    @Query(value = "SELECT * FROM product_order_tbl po WHERE po.price = :price", nativeQuery = true)
    List<Product_Order> findProductOrderByPrice(double price);

    @Query(value = "SELECT * FROM product_order_tbl po, product_tbl p WHERE po.product_id = p.id AND p.name = :name",nativeQuery = true)
    List<Product_Order> findOrderContainsProduct(String name);
}
