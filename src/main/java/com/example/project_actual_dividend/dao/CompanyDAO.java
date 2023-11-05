package com.example.project_actual_dividend.dao;

import com.example.project_actual_dividend.domain.CompanyEntity;

public interface CompanyDAO {
    boolean existedCompanyDAO(String ticker);

    void getAllCompanyDAO();

    CompanyEntity createCompanyDAO(CompanyEntity companyEntity);

    void deleteCompanyDAO();
}
