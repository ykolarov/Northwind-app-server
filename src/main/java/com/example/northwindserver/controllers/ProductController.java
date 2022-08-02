package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Product;
import com.example.northwindserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductRepository repo;

    @GetMapping("/product/id/{id}")
    public String getProductByID(@PathVariable int id, Model model){
        boolean first = false;
        Product result = repo.findById(id);
        model.addAttribute("product", result);
        model.addAttribute("list",first);
        return "productPage";
    }

    @GetMapping("/product/all")
    public String getAllProducts(Model model){
        boolean first = true;
        List<Product> result = repo.findAll();
        model.addAttribute("products",result);
        model.addAttribute("list",first);
        return "productPage";
    }

    @PostMapping({"/createProduct","/updateProduct"})
    public String createNewProduct(@ModelAttribute Product product){
        repo.save(product);
        return "redirect:/product/all";
    }

    @GetMapping("/addNewProduct")
    public String addNewProduct(Model model){
       Product product = new Product();
       model.addAttribute("product",product);
        return "newProductForm";
    }

    @GetMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable int id, Model model){
        Product productToEdit = repo.getReferenceById(id);
        model.addAttribute("productToEdit",productToEdit);
        return "productEditForm";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/product/all";
    }
}
