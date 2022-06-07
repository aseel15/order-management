package com.example.wholeProject.repositries;

import com.example.wholeProject.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//indicate that the class provides the mechanism for retrieve, update, delete,...etc on objects
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer_tbl WHERE first_name = :first_name ORDER BY last_name ASC", nativeQuery = true)
    List<Customer> findByFirstNameOrderByLastname(String first_name);


    @Query(value = "SELECT DISTINCT * FROM customer_tbl c,product_order_tbl po, order_tbl o WHERE c.id = o.customer_id AND po.product_id = o.id AND po.price >= :price",nativeQuery = true)
    List<Customer> findCustomersOrderedOverPrice(double price);

}
