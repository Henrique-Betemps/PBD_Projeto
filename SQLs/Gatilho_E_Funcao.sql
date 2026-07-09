-- Passo 1: Criar a função que será chamada pelo Trigger
CREATE OR REPLACE FUNCTION func_atualiza_data_fechamento()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se o status mudou para 'Resolvida'
    IF NEW.Status = 'Resolvida' AND OLD.Status IS DISTINCT FROM 'Resolvida' THEN
        NEW.Data_Fechamento := CURRENT_DATE;
    END IF;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Passo 2: Criar o Trigger associado à tabela Solicitacao
CREATE TRIGGER trg_fechar_solicitacao
BEFORE UPDATE ON Solicitacao
FOR EACH ROW
EXECUTE FUNCTION func_atualiza_data_fechamento();