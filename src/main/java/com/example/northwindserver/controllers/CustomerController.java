package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Customer;
import com.example.northwindserver.logger.LoggerClass;
import com.example.northwindserver.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @GetMapping("/customer/all")
    public String getAllCustomers(Model model){
        boolean first = true;
        List<Customer> result = repo.findAll();
        model.addAttribute("customers",result);
        model.addAttribute("list",first);
        LoggerClass.logTrace("Call endpoint: show all customers");
        return "customerPage";
    }

    @PostMapping({"/createCustomer","/updateCustomer"})
    public String createNewCustomer(@ModelAttribute Customer customer){
        repo.save(customer);
        LoggerClass.logTrace("Call endpoint: Create / update customer");
        return "redirect:/customer/all";
    }

    @GetMapping("/customer/add")
    public String addNewCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer",customer);
        LoggerClass.logTrace("Call endpoint: add new customer");
        return "newCustomerForm";
    }

    @GetMapping("/customer/edit/{id}")
    public String updateCustomer(@PathVariable String id, Model model){
        Customer customer = repo.getReferenceById(id);
        model.addAttribute("customerToEdit",customer);
        LoggerClass.logTrace("Call endpoint: edit customer");
        return "customerEditForm";
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable String id){
        repo.deleteById(id);
        LoggerClass.logTrace("Call endpoint: delete customer");
        return "redirect:/customer/all";
    }

}
