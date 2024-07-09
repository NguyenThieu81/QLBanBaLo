package com.example.QLBanBalo.repository;

import com.example.QLBanBalo.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}