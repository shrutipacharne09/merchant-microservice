package com.example.BaraniCaseStudy.controller;

import com.example.BaraniCaseStudy.model.Merchant;
import com.example.BaraniCaseStudy.service.MerchantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MerchantController.class)
public class MerchantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MerchantService merchantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateMerchant() throws Exception {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setDba("Test Merchant");

        Mockito.when(merchantService.createMerchant(any(Merchant.class)))
                .thenReturn(merchant);

        mockMvc.perform(post("/api/merchants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(merchant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.dba").value("Test Merchant"));
    }

    @Test
    public void testGetMerchantById() throws Exception {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setDba("Test Merchant");

        Mockito.when(merchantService.getMerchantById(1L))
                .thenReturn(Optional.of(merchant));

        mockMvc.perform(get("/api/merchants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.dba").value("Test Merchant"));
    }

    @Test
    public void testGetAllMerchants() throws Exception {
        Merchant m1 = new Merchant();
        m1.setId(1L);
        m1.setDba("M1");

        Merchant m2 = new Merchant();
        m2.setId(2L);
        m2.setDba("M2");

        Mockito.when(merchantService.getAllMerchants())
                .thenReturn(Arrays.asList(m1, m2));

        mockMvc.perform(get("/api/merchants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testDeleteMerchant() throws Exception {
        doNothing().when(merchantService).deleteMerchant(1L);

        mockMvc.perform(delete("/api/merchants/1"))
                .andExpect(status().isNoContent());
    }
}
