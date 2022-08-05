package com.example.northwindserver.http;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasketPageTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static final int PORT = 8080;

    @Test
    public void basketPageHasAppropriateContents() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/basket/show", String.class))
                .contains("Basket Details")
                .contains("Complete Order");
    }

    @Test
    public void basketPageDoesNotContainLorem() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/basket/show", String.class))
                .doesNotContain("Lorem ipsum dolor sit amet");
    }

    @Test
    public void basketPageContainsTable() throws Exception {
        assertThat(this.restTemplate.
                getForObject("http://localhost:" + 8080 + "/basket/show", String.class))
                .contains("Search")
                .contains("Next")
                .contains("Previous");
    }
}
