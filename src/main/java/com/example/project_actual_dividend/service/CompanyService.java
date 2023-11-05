package com.example.project_actual_dividend.service;

import com.example.project_actual_dividend.dto.CompanyDto;

public interface CompanyService {
    void getAllCompanyService();

    CompanyDto createCompanyService(String ticker);

    void deleteCompanyService();
}
