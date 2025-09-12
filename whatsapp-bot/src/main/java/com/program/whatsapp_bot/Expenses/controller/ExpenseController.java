package com.program.whatsapp_bot.Expenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.Request.ExpenseRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.ExpenseResponseDTO;
import com.program.whatsapp_bot.Expenses.model.Expense;

import com.program.whatsapp_bot.Expenses.service.ExpenseService;

@RestController
@RequestMapping("/api/Expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


     @PostMapping("/cadastrar")
    public ResponseEntity<ExpenseResponseDTO> cadastrar(@RequestBody ExpenseRequestDTO request) {
        Expense novaExpense = expenseService.salvar(request);
        ExpenseResponseDTO responseDTO = new ExpenseResponseDTO(novaExpense);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }   
}
