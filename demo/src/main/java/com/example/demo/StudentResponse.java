
package com.example.demo;

public class StudentResponse {

    private int id;
    private String name;
    private int age;
    private String major;
    private String address;

    public StudentResponse(
            int id, String name, int age,
            String major, String address) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public String getAddress() {
        return address;
    }
}
