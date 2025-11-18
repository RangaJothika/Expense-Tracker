package com.example.cnExpense.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

	// define the attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String username;
	@Column
	private String nickname;

	public User(String username, String nickname, String email, String address, double budget, boolean isbudgetSet) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.email = email;
		this.address = address;
		this.budget = budget;
		this.isbudgetSet = isbudgetSet;
	}

	public User() {
	}

	@Column
	private String email;
	@Column
	private String address;
	@Column
	private double budget;// budget represents a portion of the user's income that they plan to allocate
							// for expenses and sometimes the amount spend on expenses may exceed budget too
	@Column
	private boolean isbudgetSet = false;// isbudgetSet flag remains false until the budget is explicitly set using
										// setbudget method Once set,
										// the flag becomes true, regardless of the budget value, even if it's 0. but if
										// u give budget while saving user no matter it si 0 or other it will remain
										// false only .only setbudgetmethod via set will make thsi true

	public boolean isIsbudgetSet() {
		return isbudgetSet;
	}

	public void setIsbudgetSet(boolean isbudgetSet) {
		this.isbudgetSet = isbudgetSet;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Expense> expenses = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Income> incomes = new ArrayList<>();

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
}
