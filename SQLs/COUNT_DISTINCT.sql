SELECT c.Nome_Setor, COUNT(DISTINCT s.FK_Matricula) AS Alunos_Unicos
FROM Categoria c
INNER JOIN Solicitacao s ON c.ID_Categoria = s.FK_ID_Categoria
GROUP BY c.Nome_Setor;