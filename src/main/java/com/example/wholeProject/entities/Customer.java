package com.example.wholeProject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//to indicate that this class can be mapped to table
@Entity
//to specify the name of this table
@Table (name = "customer_tbl")
//create constructor of this class with all arguments
@AllArgsConstructor
//create constructor of this class without arguments
@NoArgsConstructor
//create setter & getters and toString method of the data fields of this class
@Data

public class Customer {
    //create the id of the table such that this id is auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //create a first name of type string and this column can not be null
    @Column (nullable = false, name = "firstName")
    @NotEmpty(message = "the first name should be entered ")
    private String firstName;

    @Column (nullable = false, name = "lastName")
    @NotEmpty(message = "the last name should be entered ")
    private String lastName;

    @Column(name = "bornAt")
    private LocalDate bornAt;

    //mapping between customer table and order table, such that the customer has many orders (it is the reference side)
    //this table is referenced by a foreign key in the order table
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();
}
