package com.example.project_actual_dividend.service;

import com.example.project_actual_dividend.dto.ScrapedResult;

public interface FinanceService {
    ScrapedResult findDividendByCompanyName(String companyName);
}
