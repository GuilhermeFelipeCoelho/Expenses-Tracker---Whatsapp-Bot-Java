package com.program.whatsapp_bot.Expenses.dto.Request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ExpenseRequestDTO {

    private BigDecimal valor;

    private String descricao;  

    private long usuario;

    private long categoria;
}