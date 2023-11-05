package com.example.project_actual_dividend.repository;

import com.example.project_actual_dividend.domain.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByName(String companyName);
    boolean existsByTicker(String ticker);
}
