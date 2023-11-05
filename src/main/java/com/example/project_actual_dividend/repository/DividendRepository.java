package com.example.project_actual_dividend.repository;

import com.example.project_actual_dividend.domain.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {
}
