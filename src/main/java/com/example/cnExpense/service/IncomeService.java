package com.example.cnExpense.service;

import com.example.cnExpense.dal.IncomeDal;
import com.example.cnExpense.dal.UserDal;
import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IncomeService {

	@Autowired
	private IncomeDal incomeDal;
@Autowired
private UserDal userDal;
	@Transactional
	public Income getIncomeById(Long incomeId) {
		return incomeDal.getIncomeById(incomeId);
	}

	@Transactional
	public Income saveIncome(Long userId, Income income) {
		User user = userDal.getUserById(userId);
		income.setUser(user);
		return incomeDal.saveIncome(user, income);
	}

	@Transactional
	public Income editIncome(Long incomeId, Income income) {
		Income oldIncome=getIncomeById(incomeId);
		oldIncome.setAmount(income.getAmount());
		oldIncome.setDate(income.getDate());
		oldIncome.setDescription(income.getDescription());
		oldIncome.setIncomeType(income.getIncomeType());
//		oldIncome.setUser(income.getUser());//it should be ignroed because ewe dont give user field in req body so it will set it as null so the link bw user and this income will be removed
		return incomeDal.updateIncome(incomeId, oldIncome);
	}
}