package com.example.project_actual_dividend.service.impl;

import com.example.project_actual_dividend.dao.CompanyDAO;
import com.example.project_actual_dividend.service.ScraperService;
import com.example.project_actual_dividend.dao.DividendDAO;
import com.example.project_actual_dividend.domain.CompanyEntity;
import com.example.project_actual_dividend.domain.DividendEntity;
import com.example.project_actual_dividend.dto.CompanyDto;
import com.example.project_actual_dividend.dto.ScrapedResult;
import com.example.project_actual_dividend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyDAO companyDAO;
    private final DividendDAO dividendDAO;
    private final ScraperService yahooFinanceScraperSerivce;

    @Override
    public void getAllCompanyService() {

    }

    @Override
    public CompanyDto createCompanyService(String ticker) {
        boolean exist = companyDAO.existedCompanyDAO(ticker);
        if (exist) {
            throw new RuntimeException("ticker is exist -> " + ticker);
        }

        return storeCompanyAndDividend(ticker);
    }

    private CompanyDto storeCompanyAndDividend(String ticker) {
        CompanyDto companyDto = yahooFinanceScraperSerivce
            .scrapCompanyByTicker(ticker);
        if(ObjectUtils.isEmpty(companyDto)) {
            throw new RuntimeException("failed to scrap ticker -> " + ticker);
        }

        ScrapedResult result = yahooFinanceScraperSerivce.scrap(companyDto);

        CompanyEntity companyEntity = companyDAO.createCompanyDAO(
            CompanyEntity.builder()
                .ticker(companyDto.getTicker())
                .name(companyDto.getName())
                .build()
        );

        List<DividendEntity> dividendEntities = result.getDividends().stream()
            .map(e -> DividendEntity.builder()
                .companyId(companyEntity.getId())
                .date(e.getDate())
                .dividend(e.getDividend())
                .build())
            .collect(Collectors.toList());

        this.dividendDAO.saveAllDAO(dividendEntities);
        return companyDto;
    }

    @Override
    public void deleteCompanyService() {

    }
}
