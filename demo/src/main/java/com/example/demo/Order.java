package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;

    @ManyToOne
    @JoinColumn(name ="student_id")
    private Student student;

    public Order(){}
}
