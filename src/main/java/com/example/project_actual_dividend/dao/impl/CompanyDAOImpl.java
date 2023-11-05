package com.example.project_actual_dividend.dao.impl;

import com.example.project_actual_dividend.dao.CompanyDAO;
import com.example.project_actual_dividend.repository.CompanyRepository;
import com.example.project_actual_dividend.domain.CompanyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CompanyDAOImpl implements CompanyDAO {
    private final CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean existedCompanyDAO(String ticker) {
        return companyRepository.existsByTicker(ticker);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyEntity findByNameDAO(String companyName) {
        return companyRepository.findByName(companyName)
            .orElseThrow(() -> new RuntimeException(
                "companyName is not existed ->" + companyName));
    }

    @Override
    public void getAllCompanyDAO() {

    }

    @Override
    @Transactional
    public CompanyEntity createCompanyDAO(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);
    }

    @Override
    public void deleteCompanyDAO() {

    }
}
