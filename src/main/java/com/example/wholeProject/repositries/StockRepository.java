package com.example.wholeProject.repositries;

import com.example.wholeProject.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

//indicate that the class provides the mechanism for retrieve, update, delete,...etc on objects
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Query(value = "SELECT s.quantity FROM stock_tbl s, product_tbl p WHERE s.product_id = p.id AND p.name = :name", nativeQuery = true)
    int findQuantityOfProductByName(String name);

    @Query(value = "SELECT s.update_at FROM stock_tbl s, product_tbl p WHERE s.product_id = p.id AND p.name = :name", nativeQuery = true)
    Date findUpdatedDateOfProductByName(String name);
}
