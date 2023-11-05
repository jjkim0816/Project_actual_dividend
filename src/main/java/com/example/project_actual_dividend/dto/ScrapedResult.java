package com.example.project_actual_dividend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ScrapedResult {
    private CompanyDto company;

    private List<DividendDto> dividends;

    public ScrapedResult() {
        this.dividends = new ArrayList<>();
    }
}
