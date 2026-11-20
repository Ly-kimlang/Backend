
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcom {

    @GetMapping("/welcome")
    public String greet(
            @RequestParam(defaultValue = "Guest") String name) {

        return "Welcome, " + name + "!";
    }
}
