-- Inserindo Alunos
INSERT INTO Aluno (Matricula, Nome, Email_Academico, CPF, Telefone, Curso) VALUES
('2023001', 'Ana Silva', 'ana.silva@inf.ufpel.edu.br', '111.111.111-11', '53999990001', 'Ciência da Computação'),
('2023002', 'Bruno Costa', 'bruno.costa@inf.ufpel.edu.br', '222.222.222-22', '53999990002', 'Engenharia de Computação');

-- Inserindo Atendentes
INSERT INTO Atendente (Nome, Siape, Cargo, Email, Perfil) VALUES
('Carlos Mendes', '1234567', 'Técnico em Assuntos Educacionais', 'carlos.mendes@ufpel.edu.br', 'Administrador'),
('Mariana Lima', '7654321', 'Assistente Administrativo', 'mariana.lima@ufpel.edu.br', 'Suporte');

-- Inserindo Categorias
INSERT INTO Categoria (Nome_Setor, Descricao_Setor) VALUES
('CRA', 'Coordenação de Registros Acadêmicos'),
('PRAE', 'Pró-Reitoria de Assuntos Estudantis'),
('Cobalto TI', 'Suporte técnico aos sistemas da universidade');

-- Inserindo Tags
INSERT INTO Tag (Nome_Tag) VALUES ('Matrícula'), ('Bolsa'), ('Erro no Sistema'), ('Urgente');

-- Inserindo Artigos de FAQ
INSERT INTO FAQ_Artigo (Titulo, Conteudo_Texto, Visualizacoes) VALUES
('Como solicitar histórico escolar?', 'Acesse o portal Cobalto e navegue até a aba Relatórios...', 150),
('Quais os prazos para trancamento?', 'O trancamento pode ser feito até a 4ª semana de aula...', 85);

-- Inserindo Solicitações
INSERT INTO Solicitacao (Status, Descricao_Problema, FK_Matricula, FK_ID_Categoria) VALUES
('Aberta', 'Não consigo me matricular na disciplina de Banco de Dados.', '2023001', 1),
('Em Análise', 'Dúvida sobre o edital de auxílio transporte.', '2023002', 2),
('Aberta', 'O Cobalto apresenta erro 500 ao logar.', '2023001', 3);

-- Relacionando Solicitações com Tags (Possui)
INSERT INTO Possui (FK_Protocolo, FK_ID_Tag) VALUES (1, 1), (1, 4), (2, 2), (3, 3);

-- Relacionando Atendentes com Categorias (Atua)
INSERT INTO Atua (FK_ID_Atendente, FK_ID_Categoria) VALUES (1, 1), (1, 2), (2, 3);

-- Relacionando Artigos com Tags (Indexado_por)
INSERT INTO Indexado_por (FK_ID_Artigo, FK_ID_Tag) VALUES (1, 1), (2, 1);