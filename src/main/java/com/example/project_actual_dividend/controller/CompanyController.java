package com.example.project_actual_dividend.controller;

import com.example.project_actual_dividend.dto.CompanyDto;
import com.example.project_actual_dividend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAllCompany() {
        return null;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(
        @RequestBody @Valid CompanyDto request
        ) {
        return ResponseEntity.ok(companyService
            .createCompanyService(request.getTicker()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
