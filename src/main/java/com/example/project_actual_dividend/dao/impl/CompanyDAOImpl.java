package com.example.project_actual_dividend.dao.impl;

import com.example.project_actual_dividend.dao.CompanyDAO;
import com.example.project_actual_dividend.domain.CompanyEntity;
import com.example.project_actual_dividend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CompanyDAOImpl implements CompanyDAO {
    private final CompanyRepository companyRepository;

    @Override
    public boolean existedCompanyDAO(String ticker) {
        return companyRepository.existsByTicker(ticker);
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
