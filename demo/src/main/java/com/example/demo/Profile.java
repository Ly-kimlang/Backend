package com.example.demo;
import  jakarta.persistence.*;
@Entity // class is Entity
@Table(name="profiles")// set table name
public class Profile {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)// auto matically create ID
    private int id;
    private String address;
    public Profile(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
