package com.program.whatsapp_bot.Expenses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.program.whatsapp_bot.Expenses.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNome(String nome);
}