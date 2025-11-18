package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import com.example.cnExpense.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class IncomeDalImpl implements IncomeDal {

	@Autowired
	EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Autowired
	UserService userService;

	@Override
	public Income getIncomeById(Long incomeId) {
		return getSession().get(Income.class, incomeId);
	}

	@Override
	public Income saveIncome(User user, Income newIncome) {
		getSession().save(newIncome);
		return newIncome;
	}

	@Override
	public Income updateIncome(Long incomeId, Income income) {
		getSession().update(income);
		return income;
	}

}
