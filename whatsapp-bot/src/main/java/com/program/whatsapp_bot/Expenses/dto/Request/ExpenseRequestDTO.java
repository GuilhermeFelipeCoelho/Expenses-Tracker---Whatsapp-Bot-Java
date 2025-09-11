package com.program.whatsapp_bot.Expenses.dto.Request;

import java.math.BigDecimal;

import com.program.whatsapp_bot.Expenses.enums.tipo;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ExpenseRequestDTO {
    private BigDecimal valor;
    private String descricao;
    private tipo tipo;
    private User user;
    private Category category;
}
