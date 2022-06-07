package com.example.wholeProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//to indicate that this class can be mapped to table
@Entity
//create constructor of this class with all arguments
@AllArgsConstructor
//create constructor of this class without arguments
@NoArgsConstructor
//create setter & getters and toString method of the data fields of this class
@Data
//to specify the name of this table
@Table(name = "product_tbl")

public class Product {

    //create the id of the table such that this id is auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //create a slug of type string and this column can not be null
    @Column (nullable = false)
    private String slug;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String reference;

    @Column (nullable = false)
    private double price;

    @Column (nullable = false)
    private double vat;

    @Column (nullable = false)
    private boolean stockAble;

    //mapping between stock table and product table, such that the product has many stocks
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stock> stockSet = new HashSet<>();

    //mapping between product_order table and product table, such that the product has many product orders
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product_Order> product_orders = new HashSet<>();
}
