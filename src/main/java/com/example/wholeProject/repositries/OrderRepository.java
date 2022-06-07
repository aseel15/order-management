package com.example.wholeProject.repositries;

import com.example.wholeProject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//indicate that the class provides the mechanism for retrieve, update, delete,...etc on objects
@Repository
public interface OrderRepository extends JpaRepository <Order, Integer> {
    //here I should alter the q

    @Query(value = "SELECT * FROM order_tbl o WHERE o.customer_id = :customer_id", nativeQuery = true)
    List<Order> findOrderedByCustomerId(int customer_id);

    @Query(value = "SELECT * FROM order_tbl o WHERE o.ordered_at = CURDATE()", nativeQuery = true)
    List<Order> findOrdersToday();

   /* @Query(value = "SELECT po.quantity FROM order_tbl o, product_order_tbl po WHERE o.customer_id = :customer_id AND o.id = po.product_id")
    List<Integer> findQuantityOrderedByCertainCustomer(int customer_id);*/


}
