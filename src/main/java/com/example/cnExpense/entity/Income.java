package com.example.cnExpense.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "income")
public class Income {

	// define the attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private double amount;
	@Column
	private LocalDate date;

	public Income(double amount, LocalDate date, String description, String incomeType) {
		super();
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.incomeType = incomeType;
	}

	public Income() {
	}

	@Column
	private String description;
	@Column
	private String incomeType;
	@ManyToOne()
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
}
