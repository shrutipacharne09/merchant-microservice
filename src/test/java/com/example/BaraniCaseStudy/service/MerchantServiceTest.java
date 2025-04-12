package com.example.BaraniCaseStudy.service;

import com.example.BaraniCaseStudy.exception.MerchantNotFoundException;
import com.example.BaraniCaseStudy.model.Merchant;
import com.example.BaraniCaseStudy.repository.MerchantRepository;
import com.example.BaraniCaseStudy.serviceImpl.MerchantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito Annotations
public class MerchantServiceTest {

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantServiceImpl merchantService;

    @BeforeEach
    void setUp() {
        merchantService = new MerchantServiceImpl(merchantRepository);
    }

    @Test
    public void testGetMerchantById_Success() {
        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setDba("Test Merchant");

        when(merchantRepository.findById(1L)).thenReturn(Optional.of(merchant));

        Merchant result = merchantService.getMerchantById(1L).orElse(null);

        assertNotNull(result); // Fix: using correct variable
        assertEquals(1L, result.getId());
        assertEquals("Test Merchant", result.getDba());
    }

    @Test
    void testGetMerchantById_NotFound() {
        when(merchantRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(MerchantNotFoundException.class,
                () -> merchantService.getMerchantById(100L)
                        .orElseThrow(() -> new MerchantNotFoundException("Merchant not found")));
    }
}
