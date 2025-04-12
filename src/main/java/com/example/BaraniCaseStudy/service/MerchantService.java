package com.example.BaraniCaseStudy.service;

import com.example.BaraniCaseStudy.model.Merchant;

import java.util.List;
import java.util.Optional;

public interface MerchantService {
    Merchant createMerchant(Merchant merchant);
    Optional<Merchant> getMerchantById(Long id);
    List<Merchant> getAllMerchants();
    void deleteMerchant(Long id);
}
