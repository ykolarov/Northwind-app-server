package com.example.northwindserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome.html";
    }

   // @GetMapping("/logout")
   // public String logout(){
    //    return "redirect:/login";
    //}

    /* START OF SECURITY ---- */
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }
    /* END OF SECURITY ---- */

}


