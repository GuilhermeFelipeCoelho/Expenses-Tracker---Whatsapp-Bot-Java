package com.program.whatsapp_bot.Expenses.dto.Response;

import lombok.Data;
import com.program.whatsapp_bot.Expenses.model.User;

@Data
public class UserResponseDTO {
    private Long id;
    private String waId;
    private String nome;

    public UserResponseDTO(User usuario) {
        this.id = usuario.getId();
        this.waId = usuario.getWaId();
        this.nome = usuario.getNome();
    }
}