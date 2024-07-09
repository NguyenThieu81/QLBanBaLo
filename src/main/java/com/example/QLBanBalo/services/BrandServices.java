package com.example.QLBanBalo.services;

import com.example.QLBanBalo.entity.Brand;
import com.example.QLBanBalo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServices {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public void addBrand(Brand brand) {
        brandRepository.save(brand);
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    public void updateBrand(Brand brand) {
        brandRepository.save(brand);
    }
}
