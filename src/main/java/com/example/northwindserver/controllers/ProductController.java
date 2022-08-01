package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Product;
import com.example.northwindserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository repo;

    @GetMapping("/product/id/{id}")
    public String getProductByID(@PathVariable int id, Model model){
        Product result = repo.findById(id);
        model.addAttribute("product", result);
        return "productPage";
    }

    @GetMapping("/product/all/")
    public String getAllProducts(Model model){
        List<Product> result = repo.findAll();
        model.addAttribute("products",result);
        return "productPage";
    }
}
