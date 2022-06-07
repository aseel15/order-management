package com.example.wholeProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


//to indicate that this class can be mapped to table
@Entity
//create constructor of this class with all arguments
@AllArgsConstructor
//create constructor of this class without arguments
@NoArgsConstructor
//create setter & getters and toString method of the data fields of this class
@Data
//to specify the name of this table
@Table(name = "stock_tbl")

public class Stock {
    //create the id of the table such that this id is auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //mapping between stock table and product table, such that many stocks reference to one product
    //@Column (nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    //specify the name of the column and the column must not be null
    @JoinColumn(name = "productId", nullable = false, referencedColumnName = "id")
    private Product product;

    @Column (nullable = false)
    private int quantity;

    @Column (nullable = false)
    private Date updateAt;
}
