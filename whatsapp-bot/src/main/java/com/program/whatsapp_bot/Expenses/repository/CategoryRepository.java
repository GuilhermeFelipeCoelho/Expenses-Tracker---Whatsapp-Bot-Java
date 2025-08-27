package com.program.whatsapp_bot.Expenses.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNomeAndUser(String nome, User user);

    List<Category> findByUser(User user);

    Optional<Category> findByNomeAndUserIsNull(String nome);
}