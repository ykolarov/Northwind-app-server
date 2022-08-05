package com.example.northwindserver.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderPageTest {


    @Autowired
    private TestRestTemplate restTemplate;

    private static final int PORT = 8080;

    @Test
    public void orderPageHasAppropriateContents() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/orders/all", String.class))
                .contains("Order Details");
    }

    @Test
    public void orderPageDoesNotHaveLorem() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/orders/all", String.class))
                .doesNotContain("Lorem ipsum dolor sit amet");
    }

    @Test
    public void allProductPageShouldHaveTable() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/orders/all", String.class))
                .contains("Next")
                .contains("Previous")
                .contains("Show")
                .contains("Search");
    }
}
