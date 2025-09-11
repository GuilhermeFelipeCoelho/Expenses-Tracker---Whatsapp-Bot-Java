package com.program.whatsapp_bot.Expenses.dto.Response;

import java.math.BigDecimal;

import com.program.whatsapp_bot.Expenses.enums.tipo;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.model.User;

import lombok.Data;

@Data
public class ExpenseResponseDTO {
    private BigDecimal valor;
    private String descricao;
    private tipo tipo;
    private User user;
    private Category category;

    public ExpenseResponseDTO(Expense expense){
        this.valor = expense.getValor();
        this.descricao = expense.getDescricao();
        this.tipo = expense.getTipo();
        this.user = expense.getUser();
        this.category = expense.getCategory();
    }
}
