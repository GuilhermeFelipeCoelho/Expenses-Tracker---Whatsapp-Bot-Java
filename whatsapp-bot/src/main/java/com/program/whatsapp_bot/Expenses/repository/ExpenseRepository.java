package com.program.whatsapp_bot.Expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.program.whatsapp_bot.Expenses.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}