package com.example.project_actual_dividend.dao.impl;

import com.example.project_actual_dividend.dao.DividendDAO;
import com.example.project_actual_dividend.domain.DividendEntity;
import com.example.project_actual_dividend.repository.DividendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DividendDAOImpl implements DividendDAO {
    private final DividendRepository dividendRepository;

    @Override
    @Transactional
    public void saveAllDAO(List<DividendEntity> dividendEntities) {
        this.dividendRepository.saveAll(dividendEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DividendEntity> findAllCompanyIdDAO(Long id) {
        return dividendRepository.findAllByCompanyId(id);
    }
}
