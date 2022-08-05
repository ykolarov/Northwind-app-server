package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Order;
import com.example.northwindserver.entities.Product;
import com.example.northwindserver.logger.LoggerClass;
import com.example.northwindserver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        LoggerClass.logTrace("Call endpoint: displaying product id  " +id);
        return "productPage";
    }


    @GetMapping("/product/all")
    public String getAllProducts(Model model){
        boolean first = true;
        List<Product> result = repo.findAll();
        model.addAttribute("products",result);
        model.addAttribute("list",first);
        LoggerClass.logTrace("Call endpoint: displaying all product");
        return "productPage";
    }

    @PostMapping({"/createProduct","/updateProduct"})
    public String createNewProduct(@ModelAttribute Product product){
        repo.save(product);
        LoggerClass.logTrace("Call endpoint: Creating / saving product");
        return "redirect:/product/all";
    }

    @GetMapping("/product/add")
    public String addNewProduct(Model model){
       Product product = new Product();
       List<Product> result = new ArrayList<>();
       model.addAttribute("product",product);
       LoggerClass.logTrace("Call endpoint: create new product");
        return "newProductForm";
    }

    @GetMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable int id, Model model){
        Product productToEdit = repo.getReferenceById(id);
        model.addAttribute("productToEdit",productToEdit);
        LoggerClass.logTrace("Call endpoint: edit product " +id);
        return "productEditForm";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        repo.deleteById(id);
        LoggerClass.logTrace("Call endpoint: delete product " + id);
        return "redirect:/product/all";
    }

}
