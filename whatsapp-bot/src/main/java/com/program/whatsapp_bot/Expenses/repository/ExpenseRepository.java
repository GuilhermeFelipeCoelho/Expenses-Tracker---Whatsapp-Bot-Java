package com.program.whatsapp_bot.Expenses.repository;

import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);

    List<Expense> findByUserAndCategory(User user, Category category);

    List<Expense> findByUserAndDataBetween(User user, LocalDate startDate, LocalDate endDate);

    List<Expense> findByUserAndPaymentMethod(User user, PaymentMethod paymentMethod);
}