package com.example.project_actual_dividend.dto;

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
}
