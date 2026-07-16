SELECT a.Nome AS Aluno, s.Status, c.Nome_Setor AS Setor 
FROM Solicitacao s
INNER JOIN Aluno a ON s.FK_Matricula = a.Matricula
INNER JOIN Categoria c ON s.FK_ID_Categoria = c.ID_Categoria;