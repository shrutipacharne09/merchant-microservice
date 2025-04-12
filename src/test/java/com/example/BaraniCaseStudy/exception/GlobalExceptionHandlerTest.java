package com.example.BaraniCaseStudy.exception;

import com.example.BaraniCaseStudy.controller.MerchantController;
import com.example.BaraniCaseStudy.model.Merchant;
import com.example.BaraniCaseStudy.service.MerchantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private MockMvc mockMvc;

    @Mock
    private MerchantService merchantService;

    @InjectMocks
    private MerchantController merchantController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(merchantController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testHandleMerchantNotFoundException() throws Exception {
        when(merchantService.getMerchantById(1L))
                .thenThrow(new MerchantNotFoundException("Merchant not found"));

        mockMvc.perform(get("/merchants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Merchant not found"));
    }
}
