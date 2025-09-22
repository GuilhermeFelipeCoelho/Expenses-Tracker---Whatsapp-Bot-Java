-- Adiciona a coluna 'data_atualizacao' em todas as tabelas
ALTER TABLE usuarios ADD COLUMN data_atualizacao TIMESTAMP;
ALTER TABLE categorias ADD COLUMN data_atualizacao TIMESTAMP;
ALTER TABLE transacoes ADD COLUMN data_atualizacao TIMESTAMP;

-- Adiciona a coluna 'ativo' com valor padrão 'true'
ALTER TABLE usuarios ADD COLUMN ativo BOOLEAN DEFAULT TRUE;
ALTER TABLE categorias ADD COLUMN ativo BOOLEAN DEFAULT TRUE;
ALTER TABLE transacoes ADD COLUMN ativo BOOLEAN DEFAULT TRUE;

-- Cria uma função para atualizar a data em cada modificação
CREATE OR REPLACE FUNCTION update_timestamp_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.data_atualizacao = NOW();
   RETURN NEW;
END;
$$ language 'plpgsql';

-- Associa a função às tabelas via triggers
CREATE TRIGGER update_usuarios_timestamp BEFORE UPDATE ON usuarios FOR EACH ROW EXECUTE PROCEDURE update_timestamp_column();
CREATE TRIGGER update_categorias_timestamp BEFORE UPDATE ON categorias FOR EACH ROW EXECUTE PROCEDURE update_timestamp_column();
CREATE TRIGGER update_transacoes_timestamp BEFORE UPDATE ON transacoes FOR EACH ROW EXECUTE PROCEDURE update_timestamp_column();