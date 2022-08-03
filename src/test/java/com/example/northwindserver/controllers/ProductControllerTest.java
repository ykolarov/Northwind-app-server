package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    @Test
    void getProductByIDTest(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            Product result = mapper.readValue(
                    new URL("http://localhost:8080/product/id/1"),
                    Product.class);
            String name = result.getProductName();
            Assertions.assertTrue(result.getId() == 1);
            Assertions.assertTrue(result.getProductName().equals("Chair"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}