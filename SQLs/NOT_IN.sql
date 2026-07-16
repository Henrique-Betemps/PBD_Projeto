SELECT Nome, Matricula 
FROM Aluno 
WHERE Matricula NOT IN (
    SELECT FK_Matricula FROM Solicitacao
);