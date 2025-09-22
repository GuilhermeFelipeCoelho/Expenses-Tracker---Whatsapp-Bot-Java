package com.program.whatsapp_bot.Expenses.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@SQLRestriction("ativo = true")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String waId;
    private String nome;
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo")
    private Boolean ativo = true;
}