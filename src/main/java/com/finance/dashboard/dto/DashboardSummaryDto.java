package com.finance.dashboard.dto;

import com.finance.dashboard.entity.FinancialRecord;
import java.math.BigDecimal;
import java.util.List;

 
public class DashboardSummaryDto {
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal netBalance;
    private List<CategoryTotalDto> categoryWiseExpenses;
    private List<FinancialRecord> recentActivity;    
    
	public DashboardSummaryDto(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal netBalance,
			List<CategoryTotalDto> categoryWiseExpenses, List<FinancialRecord> recentActivity) {
		super();
		this.totalIncome = totalIncome;
		this.totalExpense = totalExpense;
		this.netBalance = netBalance;
		this.categoryWiseExpenses = categoryWiseExpenses;
		this.recentActivity = recentActivity;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	public BigDecimal getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}
	public BigDecimal getNetBalance() {
		return netBalance;
	}
	public void setNetBalance(BigDecimal netBalance) {
		this.netBalance = netBalance;
	}
	public List<CategoryTotalDto> getCategoryWiseExpenses() {
		return categoryWiseExpenses;
	}
	public void setCategoryWiseExpenses(List<CategoryTotalDto> categoryWiseExpenses) {
		this.categoryWiseExpenses = categoryWiseExpenses;
	}
	public List<FinancialRecord> getRecentActivity() {
		return recentActivity;
	}
	public void setRecentActivity(List<FinancialRecord> recentActivity) {
		this.recentActivity = recentActivity;
	}    
}

