package com.program.whatsapp_bot.Expenses.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.program.whatsapp_bot.Expenses.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByWhatsappId(String whatsappId);
}


