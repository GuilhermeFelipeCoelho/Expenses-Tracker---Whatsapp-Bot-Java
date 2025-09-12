package com.program.whatsapp_bot.Expenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.whatsapp_bot.Expenses.dto.Request.ExpenserequestDTO;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.enums.tipo;
import com.program.whatsapp_bot.Expenses.repository.CategoryRepository;
import com.program.whatsapp_bot.Expenses.repository.ExpenseRepository;
import com.program.whatsapp_bot.Expenses.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoriaRepository;

    public Expense salvar(ExpenserequestDTO dto) {
        User usuario = userRepository.findById(dto.getUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Category categoria = categoriaRepository.findById(dto.getCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Expense despesa = new Expense();
        despesa.setDescricao(dto.getDescricao());
        if (dto.getValor().compareTo(BigDecimal.ZERO) < 0) {
            despesa.setTipo(tipo.despesa);
        } else {
            despesa.setTipo(tipo.receita);
        }
        despesa.setValor(dto.getValor());
        despesa.setUser(usuario);
        despesa.setCategoria(categoria);

        return expenseRepository.save(despesa);
    }

    public List<Expense> buscarTodas() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> buscarPorId(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense atualizar(Long id, ExpenserequestDTO requestDTO) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);

        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            User usuario = userRepository.findById(requestDTO.getUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Category categoria = categoriaRepository.findById(requestDTO.getCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            Expense despesa = new Expense();
            despesa.setDescricao(requestDTO.getDescricao());
            despesa.setValor(requestDTO.getValor());
            despesa.setUser(usuario);
            despesa.setCategoria(categoria);
            return expenseRepository.save(expense);
        }

        return null;
    }

    public boolean deletar(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}