package com.example.wholeProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//to indicate that this class can be mapped to table
@Entity
//create constructor of this class with all arguments
@AllArgsConstructor
//create constructor of this class without arguments
@NoArgsConstructor
//create setter & getters and toString method of the data fields of this class
@Data
//to specify the name of this table
@Table(name = "product_order_tbl")

public class Product_Order {
    //create the id of the table such that this id is auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //mapping between product_order table and product table, such that the product has many product orders
    //@Column
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "id")
    private Product product;

    //mapping between order table and product_order table, such that the order has many product orders
    //@Column
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id")
    private Order order;

    @Column (nullable = false)
    private int quantity;

    @Column (nullable = false)
    private double price;

    @Column (nullable = false)
    private double vat;


}
