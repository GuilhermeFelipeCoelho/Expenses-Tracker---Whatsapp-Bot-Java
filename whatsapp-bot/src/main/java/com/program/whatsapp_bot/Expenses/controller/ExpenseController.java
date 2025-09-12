package com.program.whatsapp_bot.Expenses.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.Request.ExpenseRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.ExpenseResponseDTO;
import com.program.whatsapp_bot.Expenses.enums.tipo;
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

    @GetMapping("/{userId}")
    public ResponseEntity<List<ExpenseResponseDTO>> getExpensesByUserId(Long id) {
        List<Expense> expenses = expenseService.buscarPorUserId(id);
        List<ExpenseResponseDTO> respostaDTO = expenses.stream()
                .map(ExpenseResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respostaDTO);
    }

    @GetMapping("/{userId}/filtered")
    public List<Expense> getFilteredExpensesByUser(
        @PathVariable Long userId,
        @RequestParam(required = false) Long categoriaId,
        @RequestParam(required = false) tipo tipo,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataInicio,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dataFim){
        LocalDateTime inicio = (dataInicio != null) ? dataInicio.atStartOfDay() : LocalDateTime.of(1970, 1, 1, 0, 0, 0);
        LocalDateTime fim = (dataFim != null) ? dataFim.atTime(LocalTime.MAX) : LocalDateTime.of(9999, 12, 31, 23, 59, 59);


        return expenseService.buscarDespesasFiltradas(userId, categoriaId, tipo, inicio, fim);
    }

}
