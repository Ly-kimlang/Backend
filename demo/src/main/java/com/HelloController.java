package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "សួស្តី! នេះគឺជា Spring Boot API ដំបូងរបស់ខ្ញុំ!";
    }
}