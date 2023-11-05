package com.example.project_actual_dividend.service;

import com.example.project_actual_dividend.dto.CompanyDto;
import com.example.project_actual_dividend.dto.ScrapedResult;

public interface ScraperService {
    CompanyDto scrapCompanyByTicker(String ticker);

    ScrapedResult scrap(CompanyDto companyDto);
}
