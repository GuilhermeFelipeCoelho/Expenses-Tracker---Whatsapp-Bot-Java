package com.program.whatsapp_bot.Expenses.dto.Response;

import lombok.Data;
import com.program.whatsapp_bot.Expenses.model.Categoria;

@Data
public class CategoriaResponseDTO {
    private Long id;
    private String nome;

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}