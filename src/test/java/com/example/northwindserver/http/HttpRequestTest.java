package com.example.northwindserver.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homepageShouldReturnNorthwindMessage() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/welcome", String.class))
                .contains("Northwind")
                .doesNotContain("Lorem ipsum dolor sit amet");
    }
    @Test
    public void loginPageShouldHaveUsernamePasswordStrings() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/login", String.class))
                .contains("Username")
                .contains("Password")
                .doesNotContain("Products")
                .doesNotContain("Lorem ipsum dolor sit amet");
    }
    @Test
    public void allProductPageShouldHaveProductDetails() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/product/all", String.class))
                .contains("Product Details")
                .contains("Search")
                .doesNotContain("Lorem ipsum dolor sit amet");
    }

    @Test
    public void basketPageHasAppropriateContents() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/basket/show", String.class))
                .contains("Basket Details")
                .contains("Search")
                .contains("Next")
                .contains("Previous")
                .contains("Complete Order")
                .doesNotContain("Lorem ipsum dolor sit amet");
    }

    @Test
    public void orderPageHasAppropriateContents() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/orders/all", String.class))
                .contains("Order Details")
                .doesNotContain("Lorem ipsum dolor sit amet");
    }
    @Test
    public void customerPageHasAppropriateContents() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/customer/all", String.class))
                .contains("Customer Details")
                .doesNotContain("Lorem ipsum dolor sit amet");
    }
}