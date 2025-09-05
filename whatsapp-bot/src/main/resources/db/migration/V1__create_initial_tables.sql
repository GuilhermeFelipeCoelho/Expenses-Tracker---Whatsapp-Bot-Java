-- Arquivo: V1__create_initial_tables.sql

-- Tabela de usuarios
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    wa_id VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(100),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de categorias
CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela de transacoes
CREATE TABLE transacoes (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT,
    categoria_id INT,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('receita', 'despesa')),
    valor DECIMAL(10, 2) NOT NULL,
    descricao TEXT,
    data_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);