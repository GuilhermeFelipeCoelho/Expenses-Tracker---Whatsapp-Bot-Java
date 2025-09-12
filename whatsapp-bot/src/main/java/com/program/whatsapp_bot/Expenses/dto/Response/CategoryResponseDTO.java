package com.program.whatsapp_bot.Expenses.dto.Response;

import lombok.Data;
import com.program.whatsapp_bot.Expenses.model.Category;

@Data
public class CategoryResponseDTO {
    private Long id;
    private String nome;

    public CategoryResponseDTO(Category categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }
}