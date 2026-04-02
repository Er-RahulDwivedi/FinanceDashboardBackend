package com.finance.dashboard.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.finance.dashboard.dto.DashboardSummaryDto;
import com.finance.dashboard.entity.TransactionType;
import com.finance.dashboard.repository.FinancialRecordRepository;

@Service
public class DashboardService {

    private final FinancialRecordRepository repository;

    public DashboardService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    public DashboardSummaryDto getSummary() {
        BigDecimal income = repository.sumAmountByType(TransactionType.INCOME);
        BigDecimal expense = repository.sumAmountByType(TransactionType.EXPENSE);
        BigDecimal net = income.subtract(expense);

        return new DashboardSummaryDto(
                income,
                expense,
                net,
                repository.getExpenseCategoryTotals(),
                repository.findTop5ByOrderByDateDesc()
        );
    }
}