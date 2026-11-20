
package com.example.demo;

import jakarta.validation.constraints.*;

public class StudentRequest {

    @NotBlank(message = "Name cannot be empty!")
    private String name;

    @Min(value = 18, message = "Age must be at least 18!")
    @Max(value = 100, message = "Age cannot exceed 100!")
    private int age;

    @NotBlank(message = "Major cannot be empty!")
    private String major;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
