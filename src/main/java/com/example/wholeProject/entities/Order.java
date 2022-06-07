package com.example.wholeProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

//to indicate that this class can be mapped to table
@Entity
//create setter & getters and toString method of the data fields of this class
@Data
//create constructor of this class with all arguments
@AllArgsConstructor
//create constructor of this class without arguments
@NoArgsConstructor
//to specify the name of this table
@Table(name = "order_tbl")

public class Order {
    //create the id of the table such that this id is auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //mapping between order table and customer table, such that many orders owned by one customer (it is the owning side)

    //@Column (nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)

    //specify more details such as the name of the column and the column must not be null
    //this column is foreign key reference to id column in the customer table
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id")
    private Customer customer;

    @Column (nullable = false)
    private Date orderedAt;



}
