package com.program.whatsapp_bot.Expenses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.whatsapp_bot.Expenses.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense>findById(Long id);
}