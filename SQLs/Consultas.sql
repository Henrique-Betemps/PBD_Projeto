-- 1. (COM JUNÇÃO) Listar todas as solicitações, mostrando o nome do aluno, o problema e o setor responsável.
SELECT s.Protocolo, a.Nome AS Aluno, c.Nome_Setor AS Setor, s.Descricao_Problema, s.Status
FROM Solicitacao s
INNER JOIN Aluno a ON s.FK_Matricula = a.Matricula
INNER JOIN Categoria c ON s.FK_ID_Categoria = c.ID_Categoria;

-- 2. (COM JUNÇÃO) Listar os atendentes e quais setores (categorias) eles gerenciam.
SELECT atd.Nome AS Atendente, atd.Perfil, c.Nome_Setor AS Setor_Atendimento
FROM Atendente atd
INNER JOIN Atua at ON atd.ID_Atendente = at.FK_ID_Atendente
INNER JOIN Categoria c ON at.FK_ID_Categoria = c.ID_Categoria;

-- 3. (COM JUNÇÃO) Buscar todos os artigos do FAQ associados à tag "Matrícula".
SELECT faq.Titulo, faq.Visualizacoes, t.Nome_Tag
FROM FAQ_Artigo faq
INNER JOIN Indexado_por ip ON faq.ID_Artigo = ip.FK_ID_Artigo
INNER JOIN Tag t ON ip.FK_ID_Tag = t.ID_Tag
WHERE t.Nome_Tag = 'Matrícula';

-- 4. (COM JUNÇÃO) Listar as tags aplicadas à solicitação do aluno "Ana Silva" (Protocolo 1).
SELECT s.Protocolo, s.Descricao_Problema, t.Nome_Tag AS Etiqueta
FROM Solicitacao s
INNER JOIN Possui p ON s.Protocolo = p.FK_Protocolo
INNER JOIN Tag t ON p.FK_ID_Tag = t.ID_Tag
WHERE s.Protocolo = 1;

-- 5. (SEM JUNÇÃO) Contagem de solicitações agrupadas por status (Para dashboards).
SELECT Status, COUNT(*) AS Total_Solicitacoes
FROM Solicitacao
GROUP BY Status;

-- 6. (SEM JUNÇÃO) Listar os artigos do FAQ mais acessados (acima de 100 visualizações).
SELECT Titulo, Conteudo_Texto, Visualizacoes
FROM FAQ_Artigo
WHERE Visualizacoes > 100
ORDER BY Visualizacoes DESC;