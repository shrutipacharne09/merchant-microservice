package com.example.BaraniCaseStudy.integration;

import com.example.BaraniCaseStudy.model.Merchant;
import com.example.BaraniCaseStudy.repository.MerchantRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MerchantIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MerchantRepository merchantRepository;

    private String baseUrl;

    private static Long createdMerchantId;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/api/merchants";
    }

    @Test
    @Order(1)
    void testCreateMerchant() {
        Merchant merchant = Merchant.builder()
                .dba("Test DBA")
                .address("Test Address")
                .url("http://test.com")
                .build();

        ResponseEntity<Merchant> response = restTemplate.postForEntity(baseUrl, merchant, Merchant.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // change to CREATED if your controller returns 201
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();

        // Store ID for other tests
        createdMerchantId = response.getBody().getId();
    }

    @Test
    @Order(2)
    void testGetAllMerchants() {
        ResponseEntity<Merchant[]> response = restTemplate.getForEntity(baseUrl, Merchant[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    @Test
    @Order(3)
    void testGetMerchantById() {
        assertThat(createdMerchantId).isNotNull();

        ResponseEntity<Merchant> response = restTemplate.getForEntity(baseUrl + "/" + createdMerchantId, Merchant.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(createdMerchantId);
    }

    @Test
    @Order(4)
    void testDeleteMerchant() {
        assertThat(createdMerchantId).isNotNull();

        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                baseUrl + "/" + createdMerchantId,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> getAfterDelete = restTemplate.getForEntity(baseUrl + "/" + createdMerchantId, String.class);
        assertThat(getAfterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @AfterAll
    static void reset() {
        createdMerchantId = null;
    }


}
