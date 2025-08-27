package com.program.whatsapp_bot.Expenses.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {

    @NotBlank(message = "O nome da categoria não pode estar em branco")
    private String nome;

    public CategoryRequestDTO() {}

    public CategoryRequestDTO(String nome) {
        this.nome = nome;
    }
}