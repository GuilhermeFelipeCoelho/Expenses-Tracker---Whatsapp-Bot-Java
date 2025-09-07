package com.program.whatsapp_bot.Expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.program.whatsapp_bot.Expenses.model.User;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<User> findByWaId(String waId);
}