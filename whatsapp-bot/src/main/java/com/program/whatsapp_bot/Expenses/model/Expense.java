package com.program.whatsapp_bot.Expenses.model;

import jakarta.persistence.Column;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Data
public class Expense {

    @Id // Esta anotação define a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esta anotação configura a geração automática do ID pelo banco
                                                        // de dados
    private Long id;

    private BigDecimal valor;

    private String descricao;

    private LocalDateTime data_transacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private tipo tipo;

    @ManyToOne // Relacionamento com a entidade Usuario
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne // Relacionamento com a entidade Categoria
    @JoinColumn(name = "categoria_id")
    private Category categoria;
}