package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Order;
import com.example.northwindserver.entities.Product;
import com.example.northwindserver.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/order/edit/{id}")
    public String editOrder(@PathVariable int id,Model model){
        Order orderToEdit = repo.getReferenceById(id);
        model.addAttribute("orderToEdit",orderToEdit);
        return "orderEditForm";

    }

    @PostMapping("/updateOrder")
    public String saveEditOrder(@ModelAttribute Order order){
        repo.save(order);
        return "redirect:orders/all";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/orders/all";
    }


}
