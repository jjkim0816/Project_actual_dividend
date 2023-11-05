package com.example.project_actual_dividend.dao;

import com.example.project_actual_dividend.domain.DividendEntity;

import java.util.List;

public interface DividendDAO {
    void saveAllDAO(List<DividendEntity> dividendEntities);

    List<DividendEntity> findAllCompanyIdDAO(Long id);
}
