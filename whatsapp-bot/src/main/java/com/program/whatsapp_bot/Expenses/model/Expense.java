package com.program.whatsapp_bot.Expenses.model;


import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.program.whatsapp_bot.Expenses.enums.tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "transacoes")
@SQLRestriction("ativo = true")
@Data
public class Expense {

    @Id // Esta anotação define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esta anotação configura a geração automática do ID pelo banco
                                                        // de dados
    private Long id;

    private BigDecimal valor;

    private String descricao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "data_transacao")
    private LocalDateTime dataTransacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private tipo tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;


    @ManyToOne // Relacionamento com a entidade Categoria
    @JoinColumn(name = "categoria_id")
    private Category categoria;
}