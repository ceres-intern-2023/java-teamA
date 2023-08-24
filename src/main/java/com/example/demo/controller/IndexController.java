package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class IndexController {
    @GetMapping
    public String index() {
        return "hello springboot 3";
    }

    @GetMapping("/news")
    public String news() {
        return "hello springboot 3!";
    }
}
