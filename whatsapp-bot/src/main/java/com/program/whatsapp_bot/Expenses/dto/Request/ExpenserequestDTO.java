package com.program.whatsapp_bot.Expenses.dto.Request;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ExpenserequestDTO {

    private BigDecimal valor;

    private String descricao;  

    private long usuario;

    private long categoria;
}