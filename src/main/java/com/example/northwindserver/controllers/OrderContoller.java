package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Order;
import com.example.northwindserver.entities.Product;
import com.example.northwindserver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderContoller {

    @Autowired
    OrderRepository repo;

    @GetMapping("/orders/all")
    public String getAllOrders(Model model) {
        List<Order> result = repo.findAll();
        model.addAttribute("orders", result);
        return "ordersPage";
    }


}
