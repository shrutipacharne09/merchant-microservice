package com.example.BaraniCaseStudy.serviceImpl;

import com.example.BaraniCaseStudy.exception.MerchantNotFoundException;
import com.example.BaraniCaseStudy.model.Merchant;
import com.example.BaraniCaseStudy.repository.MerchantRepository;
import com.example.BaraniCaseStudy.service.MerchantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Optional<Merchant> getMerchantById(Long id) {
        return merchantRepository.findById(id);
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public void deleteMerchant(Long id) {
        if (!merchantRepository.existsById(id)) {
            throw new MerchantNotFoundException("Merchant with ID " + id + " not found.");
        }
        merchantRepository.deleteById(id);
    }
}
