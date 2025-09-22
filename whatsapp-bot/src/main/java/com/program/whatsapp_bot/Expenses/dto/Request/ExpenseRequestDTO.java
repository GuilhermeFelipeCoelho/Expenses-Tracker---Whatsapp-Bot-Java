package com.program.whatsapp_bot.Expenses.dto.Request;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenseRequestDTO {

    private BigDecimal valor;

    private String descricao;  

    private Long usuario;

    private Long categoria;
}
