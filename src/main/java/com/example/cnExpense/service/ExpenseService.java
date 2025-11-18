package com.example.cnExpense.service;

import com.example.cnExpense.dal.ExpenseDal;
import com.example.cnExpense.dal.UserDal;
import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.entity.User;
import com.example.cnExpense.exception.BudgetExceedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseDal expenseDal;
	@Autowired
	private UserDal userDal;

	@Transactional
	public Expense saveExpense(Long userId, Expense expense) {
		User user = userDal.getUserById(userId);
		expense.setUser(user);
		if (user.isIsbudgetSet() == true) {
			double sum = userDal.getTotalExpense(userId) + expense.getAmount();
			if (sum > user.getBudget()) {
				throw new BudgetExceedException("Expense exceeds the set budget.");
		}
		}
			expenseDal.saveExpense(userId, expense);
		return expense;
	}

	@Transactional
	public Expense getExpenseById(Long expenseId) {
		return expenseDal.getExpenseById(expenseId);
	}
}