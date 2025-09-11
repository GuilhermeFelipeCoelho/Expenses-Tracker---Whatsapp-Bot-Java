package com.program.whatsapp_bot.Expenses.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/expense")
public class ExpenseController {
    @PostMapping("salvar")
    public String postMethodName(@RequestBody String entity) {

        return entity;
    }
}