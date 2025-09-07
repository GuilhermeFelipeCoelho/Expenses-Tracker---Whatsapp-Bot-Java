package com.program.whatsapp_bot.Expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.program.whatsapp_bot.Expenses.model.Categoria;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);
}