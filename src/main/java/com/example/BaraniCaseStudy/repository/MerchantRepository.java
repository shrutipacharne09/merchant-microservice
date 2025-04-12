package com.example.BaraniCaseStudy.repository;

import com.example.BaraniCaseStudy.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    List<Merchant> findByAddressContaining(String address);
}
