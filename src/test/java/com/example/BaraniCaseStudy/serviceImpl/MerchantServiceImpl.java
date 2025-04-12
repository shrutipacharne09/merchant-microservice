package com.example.BaraniCaseStudy.serviceImpl;

import com.example.BaraniCaseStudy.exception.MerchantNotFoundException;
import com.example.BaraniCaseStudy.model.Merchant;
import com.example.BaraniCaseStudy.repository.MerchantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MerchantServiceImplTest {

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantServiceImpl merchantService;

    private Merchant sampleMerchant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleMerchant = new Merchant();
        sampleMerchant.setId(1L);
        sampleMerchant.setDba("Barani Stores");
        sampleMerchant.setAddress("Bangalore");
        sampleMerchant.setUrl("http://baranistores.com");
    }

    @Test
    void createMerchant_shouldSaveAndReturnMerchant() {
        when(merchantRepository.save(sampleMerchant)).thenReturn(sampleMerchant);

        Merchant result = merchantService.createMerchant(sampleMerchant);

        assertNotNull(result);
        assertEquals("Barani Stores", result.getDba());
        verify(merchantRepository, times(1)).save(sampleMerchant);
    }

    @Test
    void getMerchantById_found_shouldReturnMerchant() {
        when(merchantRepository.findById(1L)).thenReturn(Optional.of(sampleMerchant));

        Optional<Merchant> result = merchantService.getMerchantById(1L);

        assertTrue(result.isPresent());
        assertEquals("Barani Stores", result.get().getDba());
    }

    @Test
    void getMerchantById_notFound_shouldReturnEmpty() {
        when(merchantRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Merchant> result = merchantService.getMerchantById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void getAllMerchants_shouldReturnList() {
        List<Merchant> mockList = List.of(sampleMerchant);
        when(merchantRepository.findAll()).thenReturn(mockList);

        List<Merchant> result = merchantService.getAllMerchants();

        assertEquals(1, result.size());
        assertEquals("Barani Stores", result.get(0).getDba());
    }

    @Test
    void deleteMerchant_existingId_shouldDeleteWithoutException() {
        when(merchantRepository.existsById(1L)).thenReturn(true);
        doNothing().when(merchantRepository).deleteById(1L);

        assertDoesNotThrow(() -> merchantService.deleteMerchant(1L));
        verify(merchantRepository).deleteById(1L);
    }

    @Test
    void deleteMerchant_nonExistingId_shouldThrowException() {
        when(merchantRepository.existsById(99L)).thenReturn(false);

        MerchantNotFoundException ex = assertThrows(
                MerchantNotFoundException.class,
                () -> merchantService.deleteMerchant(99L)
        );

        assertEquals("Merchant with ID 99 not found.", ex.getMessage());
        verify(merchantRepository, never()).deleteById(anyLong());
    }
}
