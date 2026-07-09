-- Criação das Tabelas Independentes (Sem Foreign Keys)

CREATE TABLE Aluno (
    Matricula VARCHAR(20) PRIMARY KEY, -- [cite: 166]
    Nome VARCHAR(100) NOT NULL,
    Email_Academico VARCHAR(100) UNIQUE NOT NULL,
    CPF VARCHAR(14) UNIQUE NOT NULL,
    Telefone VARCHAR(15),
    Curso VARCHAR(50) NOT NULL
);

CREATE TABLE Atendente (
    ID_Atendente SERIAL PRIMARY KEY, -- [cite: 167]
    Nome VARCHAR(100) NOT NULL,
    Siape VARCHAR(20) UNIQUE NOT NULL,
    Cargo VARCHAR(50),
    Email VARCHAR(100) UNIQUE NOT NULL,
    Perfil VARCHAR(30) NOT NULL -- Ex: 'Administrador', 'Suporte'
);

CREATE TABLE Categoria (
    ID_Categoria SERIAL PRIMARY KEY, -- [cite: 168]
    Nome_Setor VARCHAR(50) NOT NULL,
    Descricao_Setor TEXT
);

CREATE TABLE FAQ_Artigo (
    ID_Artigo SERIAL PRIMARY KEY, -- [cite: 172]
    Titulo VARCHAR(150) NOT NULL,
    Conteudo_Texto TEXT NOT NULL,
    Data_Publicacao DATE DEFAULT CURRENT_DATE,
    Visualizacoes INT DEFAULT 0
);

CREATE TABLE Tag (
    ID_Tag SERIAL PRIMARY KEY, -- [cite: 173]
    Nome_Tag VARCHAR(50) UNIQUE NOT NULL
);

-- Criação das Tabelas Dependentes (Com Foreign Keys)

CREATE TABLE Solicitacao (
    Protocolo SERIAL PRIMARY KEY, -- [cite: 169]
    Status VARCHAR(20) NOT NULL DEFAULT 'Aberta',
    Data_Fechamento DATE,
    Descricao_Problema TEXT NOT NULL,
    Data_Abertura DATE DEFAULT CURRENT_DATE,
    FK_Matricula VARCHAR(20) NOT NULL,
    FK_ID_Categoria INT NOT NULL,
    CONSTRAINT fk_aluno FOREIGN KEY (FK_Matricula) REFERENCES Aluno(Matricula), -- [cite: 170]
    CONSTRAINT fk_categoria FOREIGN KEY (FK_ID_Categoria) REFERENCES Categoria(ID_Categoria) -- [cite: 171]
);

-- Tabelas Associativas (Relacionamentos N:M)

CREATE TABLE Possui (
    FK_Protocolo INT, -- [cite: 174]
    FK_ID_Tag INT,
    PRIMARY KEY (FK_Protocolo, FK_ID_Tag),
    CONSTRAINT fk_solicitacao_possui FOREIGN KEY (FK_Protocolo) REFERENCES Solicitacao(Protocolo), -- [cite: 175]
    CONSTRAINT fk_tag_possui FOREIGN KEY (FK_ID_Tag) REFERENCES Tag(ID_Tag) -- [cite: 176]
);

CREATE TABLE Atua (
    FK_ID_Atendente INT, -- [cite: 177]
    FK_ID_Categoria INT,
    PRIMARY KEY (FK_ID_Atendente, FK_ID_Categoria),
    CONSTRAINT fk_atendente_atua FOREIGN KEY (FK_ID_Atendente) REFERENCES Atendente(ID_Atendente), -- [cite: 178]
    CONSTRAINT fk_categoria_atua FOREIGN KEY (FK_ID_Categoria) REFERENCES Categoria(ID_Categoria) -- [cite: 179]
);

CREATE TABLE Indexado_por (
    FK_ID_Artigo INT, -- [cite: 180]
    FK_ID_Tag INT,
    PRIMARY KEY (FK_ID_Artigo, FK_ID_Tag),
    CONSTRAINT fk_artigo_indexado FOREIGN KEY (FK_ID_Artigo) REFERENCES FAQ_Artigo(ID_Artigo), -- [cite: 181]
    CONSTRAINT fk_tag_indexado FOREIGN KEY (FK_ID_Tag) REFERENCES Tag(ID_Tag) -- [cite: 182, 183]
);