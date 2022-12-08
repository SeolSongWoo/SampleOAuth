package com.example.webstudyproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @GetMapping(value = "/debouncing")
    public String Debouncing() {
        return "debouncing";
    }

    @GetMapping(value = "/throttling")
    public String Throttling () {
        return "throttling";
    }

    @GetMapping("/loginpage")
    public String LoginPage() {
        return "loginpage";
    }
}
