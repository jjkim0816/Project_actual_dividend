package com.example.project_actual_dividend.service.impl;

import com.example.project_actual_dividend.dao.CompanyDAO;
import com.example.project_actual_dividend.service.FinanceService;
import com.example.project_actual_dividend.dao.DividendDAO;
import com.example.project_actual_dividend.domain.CompanyEntity;
import com.example.project_actual_dividend.domain.DividendEntity;
import com.example.project_actual_dividend.dto.CompanyDto;
import com.example.project_actual_dividend.dto.DividendDto;
import com.example.project_actual_dividend.dto.ScrapedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {
    private final CompanyDAO companyDAO;
    private final DividendDAO dividendDAO;
    @Override
    @Cacheable(key = "#companyName", value = "finance")
    public ScrapedResult findDividendByCompanyName(String companyName) {
        CompanyEntity company = this.companyDAO.findByNameDAO(companyName);

        CompanyDto companyDto = CompanyDto.builder()
            .ticker(company.getTicker())
            .name(company.getName())
            .build();

        List<DividendEntity> dividendEntities = this.dividendDAO
            .findAllCompanyIdDAO(company.getId());

        List<DividendDto> dividendDtos = dividendEntities.stream()
            .map(e -> DividendDto.builder()
                .date(e.getDate())
                .dividend(e.getDividend())
                .build())
            .collect(Collectors.toList());

        return ScrapedResult.builder()
            .company(companyDto)
            .dividends(dividendDtos)
            .build();
    }
}
