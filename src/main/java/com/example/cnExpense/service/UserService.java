package com.example.cnExpense.service;

import com.example.cnExpense.dal.UserDal;
import com.example.cnExpense.entity.Expense;
import com.example.cnExpense.entity.Income;
import com.example.cnExpense.entity.User;
import com.example.cnExpense.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDal userDal;

	@Transactional
	public User getUserById(Long id) {
		return userDal.getUserById(id);
	}

	@Transactional
	public User saveUser(User user) {
		return userDal.saveUser(user);
	}

	@Transactional
	public User editUser(Long userId, User user) {
		User oldUser = getUserById(userId);
		if (oldUser == null)
			throw new NotFoundException(" the id is not found");
		oldUser.setAddress(user.getAddress());
		oldUser.setBudget(user.getBudget());
		oldUser.setEmail(user.getEmail());
		oldUser.setIsbudgetSet(user.isIsbudgetSet());
		oldUser.setNickname(user.getNickname());
		oldUser.setUsername(user.getUsername());

//		 as incomes and expenses are bidirectional mapped fields with user,both sides need to be set for each other ,in first way we only set users income directly not income's user 
//			oldUser.setIncomes(user.getIncomes());
		oldUser.getIncomes().clear();//removes all existing Income objects from the user’s in-memory and in turn as we have update when we run the app it updates the db according to hte schemas in memory so it is also removed from db(removes means make it empty list,not null)
		if (user.getIncomes() != null) {
			for (Income income : user.getIncomes()) {
				income.setUser(oldUser); // <-- attach parent
				oldUser.getIncomes().add(income);//cant use setincome method here as set income is used to set a list,but here we had individual income objs ,so we addeach tothe empty list we got after we clear it
			}
		}

//		    oldUser.setExpenses(user.getExpenses());
		oldUser.getExpenses().clear();
		if (user.getExpenses() != null) {
			for (Expense expense : user.getExpenses()) {
				expense.setUser(oldUser); // <-- attach parent
				oldUser.getExpenses().add(expense);
			}
		}
		return userDal.updateUser(userId, oldUser);
//		return oldUser;
	}

	@Transactional
	public User setBudget(Long userId, double budget) {
		User user = getUserById(userId);
		user.setBudget(budget);
		user.setIsbudgetSet(true);
		return userDal.setBudget(userId, budget);
	}

	@Transactional
	public double getTotalExpense(Long userId) {
		return userDal.getTotalExpense(userId);
	}

	@Transactional
	public double getQuotation(Long userId) {
		return userDal.getQuotation(userId);
	}

	@Transactional
	public double getAvgExpenseData(Long userId) {
		return userDal.getAvgExpenseData(userId);
	}
}