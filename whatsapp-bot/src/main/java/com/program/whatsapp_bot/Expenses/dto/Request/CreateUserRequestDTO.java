package com.program.whatsapp_bot.Expenses.dto.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@Data
public class CreateUserRequestDTO {
    private String waId;
    private String nome;
}