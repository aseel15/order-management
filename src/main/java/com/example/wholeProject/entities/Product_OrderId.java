package com.example.wholeProject.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class Product_OrderId implements Serializable {

    @Column(name = "productId")
    private int productId;

    @Column(name = "orderId")
    private int orderId;
}
