SELECT c.Nome_Setor, COUNT(s.Protocolo) AS Total_Solicitacoes
FROM Categoria c
INNER JOIN Solicitacao s ON c.ID_Categoria = s.FK_ID_Categoria
GROUP BY c.Nome_Setor
HAVING COUNT(s.Protocolo) > 5;