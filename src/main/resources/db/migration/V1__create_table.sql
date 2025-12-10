USE TarefasDB;
GO

CREATE TABLE tb_tarefas (
    id INT IDENTITY(1,1) PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(500),
    data_criacao DATETIME DEFAULT GETDATE(),
    status VARCHAR(20) DEFAULT 'PENDENTE'
);
