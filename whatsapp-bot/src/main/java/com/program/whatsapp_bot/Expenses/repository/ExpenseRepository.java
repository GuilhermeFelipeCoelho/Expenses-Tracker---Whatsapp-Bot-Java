package com.program.whatsapp_bot.Expenses.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.whatsapp_bot.Expenses.enums.tipo;
import com.program.whatsapp_bot.Expenses.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    
    List<Expense> findByUserIdAndTipo (Long userId,tipo tipo);

    List<Expense> findByUserIdAndCategoriaId(Long userId, Long categoriaId);
    
    List<Expense> findByUserIdAndCategoriaIdAndTipo(Long userId, Long categoriaId, tipo tipo);

    List<Expense> findByUserIdAndTipoAndDataTransacaoBetween(Long userId, tipo tipo, LocalDateTime inicio, LocalDateTime fim);

    List<Expense> findByUserIdAndCategoriaIdAndTipoAndDataTransacaoBetween(Long userId, Long categoriaId, tipo tipo, LocalDateTime inicio, LocalDateTime fim);

    List<Expense> findByUserIdAndDataTransacaoBetween(Long userId, LocalDateTime inicio, LocalDateTime fim);

    List<Expense> findByUserIdAndCategoriaIdAndDataTransacaoBetween(Long userId, Long categoriaId, LocalDateTime inicio, LocalDateTime fim);
}