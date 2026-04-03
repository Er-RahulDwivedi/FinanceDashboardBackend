package com.finance.dashboard.repository;

import com.finance.dashboard.dto.CategoryTotalDto;
import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {
    
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = :type")
    BigDecimal sumAmountByType(TransactionType type);

    @Query("SELECT new com.finance.dashboard.dto.CategoryTotalDto(f.category, SUM(f.amount)) " +
           "FROM FinancialRecord f WHERE f.type = 'EXPENSE' GROUP BY f.category")
    
    List<CategoryTotalDto> getExpenseCategoryTotals();

    List<FinancialRecord> findTop5ByOrderByDateDesc();
}