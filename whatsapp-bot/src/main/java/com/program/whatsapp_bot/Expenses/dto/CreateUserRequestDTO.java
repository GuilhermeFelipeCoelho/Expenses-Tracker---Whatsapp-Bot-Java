package com.program.whatsapp_bot.Expenses.dto;

import java.util.List;

public class CreateUserRequestDTO {
    private String nome;
    private String whatsappId;
    private boolean usarCategoriasPadrao;
    private List<String> categoriasPersonalizadas;
    
    public CreateUserRequestDTO() {}

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getWhatsappId() {
        return whatsappId;
    }
    public void setWhatsappId(String whatsappId) {
        this.whatsappId = whatsappId;
    }
    public boolean isUsarCategoriasPadrao() {
        return usarCategoriasPadrao;
    }
    public void setUsarCategoriasPadrao(boolean usarCategoriasPadrao) {
        this.usarCategoriasPadrao = usarCategoriasPadrao;
    }
    public List<String> getCategoriasPersonalizadas() {
        return categoriasPersonalizadas;
    }
    public void setCategoriasPersonalizadas(List<String> categoriasPersonalizadas) {
        this.categoriasPersonalizadas = categoriasPersonalizadas;
    }
}
