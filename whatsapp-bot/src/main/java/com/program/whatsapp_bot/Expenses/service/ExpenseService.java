package com.program.whatsapp_bot.Expenses.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.whatsapp_bot.Expenses.dto.Request.ExpenseRequestDTO;
import com.program.whatsapp_bot.Expenses.enums.tipo;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.repository.CategoryRepository;
import com.program.whatsapp_bot.Expenses.repository.ExpenseRepository;
import com.program.whatsapp_bot.Expenses.repository.UserRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoriaRepository;

    public Expense salvar(ExpenseRequestDTO dto) {
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

    public List<Expense> buscarPorUserId(Long id) {
        return expenseRepository.findByUserId(id);
    }

    public List<Expense> buscarDespesasFiltradas(Long userId, Long categoriaId, tipo tipo, LocalDateTime inicio,
            LocalDateTime fim) {

        boolean temCategoria = categoriaId != null;
        boolean temDatas = inicio != null && fim != null;
        boolean temTipo = tipo != null;

        if (temCategoria && temDatas && temTipo) {
            return expenseRepository.findByUserIdAndCategoriaIdAndTipoAndDataTransacaoBetween(userId, categoriaId, tipo,
                    inicio, fim);
        }
        if (temCategoria && temDatas) {
            return expenseRepository.findByUserIdAndCategoriaIdAndDataTransacaoBetween(userId, categoriaId, inicio,
                    fim);
        }
        if (temCategoria && temTipo) {
            return expenseRepository.findByUserIdAndCategoriaIdAndTipo(userId, categoriaId, tipo);
        }
        if (temDatas && temTipo) {
            return expenseRepository.findByUserIdAndTipoAndDataTransacaoBetween(userId, tipo, inicio, fim);
        }
        if (temCategoria) {
            return expenseRepository.findByUserIdAndCategoriaId(userId, categoriaId);
        }
        if (temTipo) {
            return expenseRepository.findByUserIdAndTipo(userId, tipo);
        }
        if (temDatas) {
            return expenseRepository.findByUserIdAndDataTransacaoBetween(userId, inicio, fim);
        }

        return expenseRepository.findByUserId(userId);
    }

    public Expense atualizar(Long id, ExpenseRequestDTO requestDTO) {
        Optional<Expense> expenseOptional = expenseRepository.findById(id);

        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            Category categoria = categoriaRepository.findById(requestDTO.getCategoria())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

            expense.setDescricao(requestDTO.getDescricao());
            expense.setValor(requestDTO.getValor());
            expense.setCategoria(categoria);
            if (expense.getValor().compareTo(BigDecimal.ZERO) < 0) {
                expense.setTipo(tipo.despesa);
            } else {
                expense.setTipo(tipo.receita);
            }

            return expenseRepository.save(expense);
        }

        return null;
    }

    public boolean deletar(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
        if(expense != null){
            expense.setAtivo(false);
            expenseRepository.save(expense);
            return true;
        }
        return false;
    }
}