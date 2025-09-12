package com.program.whatsapp_bot.Expenses.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.Request.ExpenseRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.ExpenseResponseDTO;
import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.service.ExpenseService;


@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


     @PostMapping("/cadastrar")
    public ResponseEntity<ExpenseResponseDTO> cadastrar(@RequestBody ExpenseRequestDTO request) {
        Expense novaExpense = expenseService.salvar(request);
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(novaExpense);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/user{id}")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByUserId(Long id) {
        List<Expense> expenses = expenseService.buscarPorUserId(id);
        List<ExpenseResponseDTO> respostaDTO = expenses.stream()
            .map(ExpenseResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(respostaDTO);
    }
}
