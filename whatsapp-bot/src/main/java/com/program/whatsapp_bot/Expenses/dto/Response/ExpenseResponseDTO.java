package com.program.whatsapp_bot.Expenses.dto.Response;

import java.math.BigDecimal;

import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.model.tipo;


import lombok.Data;

@Data
public class ExpenseResponseDTO {    
    private Long id;

    private BigDecimal valor;

    private String descricao;
    
   // private LocalDateTime data_transacao = LocalDateTime.now();

    private tipo tipo;

    private User usuario;

    private Category categoria;

    public ExpenseResponseDTO(Expense expense) {
        this.id = expense.getId();
        this.descricao = expense.getDescricao();
        this.valor = expense.getValor();
        this.categoria = expense.getCategoria();
        this.tipo =expense.getTipo();
        this.usuario = expense.getUsuario();
    }
}
