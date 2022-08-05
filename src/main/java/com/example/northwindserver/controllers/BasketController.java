package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Order;
import com.example.northwindserver.entities.Product;
import com.example.northwindserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("basket")
public class BasketController {

    @Autowired
    ProductRepository repo;

    @GetMapping("/basket/add/{id}")
    public String addToBasket(@PathVariable int id, Model model ,@ModelAttribute("basket") List<Product> basket){
        Product product = repo.getReferenceById(id);
        basket.add(product);
        model.addAttribute("basket",basket);
        return "basketPage";
    }

    @GetMapping("/basket/show/")
    public String showBasket(@ModelAttribute("basket") List<Product> basket, Model model){
        model.addAttribute("basket",basket);
        return "basketPage";
    }

    @GetMapping("/basket/delete/{id}")
    public String deleteFromBasket(@PathVariable int id,@ModelAttribute("basket") List<Product> basket){
        basket.remove(id);
        return "redirect:/basket/show/";
    }
    @ModelAttribute("basket")
    public List<Product> basket(){
        return new ArrayList<>();
    }


    @PostMapping("/product/submitOrder")
    public String submitOrder(Model model){
        Order order = new Order();
        model.addAttribute("basket",order);
        return "SubmitOrder";
    }
}
