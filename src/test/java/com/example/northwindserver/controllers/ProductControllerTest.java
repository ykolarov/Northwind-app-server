package com.example.northwindserver.controllers;

import com.example.northwindserver.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController controller;
    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

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