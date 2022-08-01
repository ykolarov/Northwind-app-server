package com.example.northwindserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome.html";
    }
}
