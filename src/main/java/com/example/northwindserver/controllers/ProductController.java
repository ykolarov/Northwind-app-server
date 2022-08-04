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

    @GetMapping("/product/add")
    public String addNewProduct(Model model){
       Product product = new Product();
       List<Product> result = new ArrayList<>();

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

    @PostMapping("/product/submitOrder")
    public String submitOrder(Model model){
        Order order = new Order();
        model.addAttribute("basket",order);
        return "SubmitOrder";
    }
}
