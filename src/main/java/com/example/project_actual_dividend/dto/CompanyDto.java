package com.example.project_actual_dividend.dto;

import com.example.project_actual_dividend.domain.CompanyEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto {
    @NotBlank
    private String ticker;
    private String name;

    public static CompanyDto fromEntity(CompanyEntity company) {
        return CompanyDto.builder()
            .ticker(company.getTicker())
            .name(company.getName())
            .build();
    }
}
