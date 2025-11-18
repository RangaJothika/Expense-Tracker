package com.example.cnExpense.dal;

import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDalImpl implements UserDal {

	@Autowired
	EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public User getUserById(Long id) {
		return getSession().get(User.class, id);
	}

	@Override
	public User saveUser(User user) {
		getSession().save(user);
		return user;
	}

//below methdos are included here as the interface this class implemtns hase it which is given by course
//	better to avoid the below methods and have them in service alone except update user as hibernate takes care of it auto
	@Override
	public User updateUser(Long userId, User user) {
//		getSession().update(user);
		return user;
	}

	@Override
	public User setBudget(Long userId, double budget) {
		User user = getUserById(userId);
		return user;
	}

	@Override
	public double getTotalExpense(Long userId) {
		double totalExpense = 0;
		User user = getUserById(userId);
		List<Expense> expenses = user.getExpenses();
		for (Expense expense : expenses) {
			totalExpense += expense.getAmount();
		}
		return totalExpense;
	}

	@Override
	public double getQuotation(Long userId) {
		User user = getUserById(userId);
		double totalExpense = getTotalExpense(userId);
		double totalIncome = 0;
		List<Income> incomes = user.getIncomes();
		for (Income income : incomes) {
			totalIncome += income.getAmount();
		}
		return totalIncome - totalExpense;// if income<expense,it returns negative number(like u had debt(lent money))
	}

	@Override
	public double getAvgExpenseData(Long userId) {
		double totalExpense = getTotalExpense(userId);
		User user = getUserById(userId);
		double totalExpensesCount = user.getExpenses().size();
		return totalExpense / totalExpensesCount;
	}

}
