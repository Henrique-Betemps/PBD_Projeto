package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gestor_Solicitacao {

    // 1. INSERÇÃO (Create)
    // Agora o método recebe os dados da tela para inserir no banco
    public void abrirSolicitacao(String descricao, String matricula, int idCategoria) {
        String sql = "INSERT INTO Solicitacao (Descricao_Problema, FK_Matricula, FK_ID_Categoria) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Substitui os '?' na string SQL pelos valores recebidos
            stmt.setString(1, descricao);
            stmt.setString(2, matricula);
            stmt.setInt(3, idCategoria);
            stmt.executeUpdate(); // Executa a inserção

            System.out.println("Solicitação aberta com sucesso no banco!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 2. CONSULTA (Read)
    // Faz um SELECT com JOIN e retorna uma lista formatada para mostrar na tela
    public List<String> listarSolicitacoes() {
        List<String> lista = new ArrayList<>();

        String sql = "SELECT s.Protocolo, a.Nome AS Aluno, c.Nome_Setor AS Setor, s.Status " +
                "FROM Solicitacao s " +
                "INNER JOIN Aluno a ON s.FK_Matricula = a.Matricula " +
                "INNER JOIN Categoria c ON s.FK_ID_Categoria = c.ID_Categoria";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // executeQuery é usado para SELECT

            // O ResultSet (rs) percorre linha por linha do resultado do banco
            while (rs.next()) {
                String linha = "Protocolo " + rs.getInt("Protocolo") +
                        " | Aluno: " + rs.getString("Aluno") +
                        " | Setor: " + rs.getString("Setor") +
                        " | Status: " + rs.getString("Status");
                lista.add(linha);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao consultar: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // 3. ATUALIZAÇÃO (Update)
    // Atualiza o status de um chamado (ex: de 'Aberta' para 'Resolvida')
    public void atualizarStatus(int protocolo, String novoStatus) {
        String sql = "UPDATE Solicitacao SET Status = ? WHERE Protocolo = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, protocolo);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Status do protocolo " + protocolo + " atualizado para '" + novoStatus + "'.");
            } else {
                System.out.println("Nenhuma solicitação encontrada com o protocolo " + protocolo + ".");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método otimizado para preencher tabelas do Java Swing
    public javax.swing.table.DefaultTableModel listarParaTabela() {
        // Define as colunas da tabela
        String[] colunas = {"Protocolo", "Aluno", "Setor", "Status"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(colunas, 0);

        String sql = "SELECT s.Protocolo, a.Nome AS Aluno, c.Nome_Setor AS Setor, s.Status " +
                "FROM Solicitacao s " +
                "INNER JOIN Aluno a ON s.FK_Matricula = a.Matricula " +
                "INNER JOIN Categoria c ON s.FK_ID_Categoria = c.ID_Categoria " +
                "ORDER BY s.Protocolo DESC"; // Mostra os mais recentes primeiro

        try (java.sql.Connection conn = ConexaoBD.conectar();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            // Lê o banco e adiciona as linhas no modelo visual
            while (rs.next()) {
                Object[] linha = {
                        rs.getInt("Protocolo"),
                        rs.getString("Aluno"),
                        rs.getString("Setor"),
                        rs.getString("Status")
                };
                modelo.addRow(linha);
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao listar para a tabela: " + e.getMessage());
        }

        return modelo;
    }

    // 4. INSERÇÃO DE FAQ (Uso restrito para Administradores)
    public void cadastrarFaq(String titulo, String conteudo) {
        // A data de publicação e as visualizações são preenchidas automaticamente pelo banco (DEFAULT)
        String sql = "INSERT INTO FAQ_Artigo (Titulo, Conteudo_Texto) VALUES (?, ?)";

        try (java.sql.Connection conn = ConexaoBD.conectar();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, conteudo);
            stmt.executeUpdate();

            System.out.println("Artigo de FAQ cadastrado com sucesso no banco!");

        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao inserir FAQ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 5. CONSULTA DE FAQ (Para preencher a tabela visual)
    public javax.swing.table.DefaultTableModel listarFaqsParaTabela() {
        String[] colunas = {"ID", "Título", "Data de Publicação", "Visualizações"};
        javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(colunas, 0);

        String sql = "SELECT ID_Artigo, Titulo, Data_Publicacao, Visualizacoes FROM FAQ_Artigo ORDER BY ID_Artigo DESC";

        try (java.sql.Connection conn = ConexaoBD.conectar();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Object[] linha = {
                        rs.getInt("ID_Artigo"),
                        rs.getString("Titulo"),
                        rs.getDate("Data_Publicacao"),
                        rs.getInt("Visualizacoes")
                };
                modelo.addRow(linha);
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao listar FAQs: " + e.getMessage());
        }

        return modelo;
    }

    // 6. INCREMENTAR VISUALIZAÇÃO DO FAQ
    public void registrarVisualizacaoFaq(int idArtigo) {
        String sql = "UPDATE FAQ_Artigo SET Visualizacoes = Visualizacoes + 1 WHERE ID_Artigo = ?";
        try (java.sql.Connection conn = ConexaoBD.conectar();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idArtigo);
            stmt.executeUpdate();
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao contabilizar visualização: " + e.getMessage());
        }
    }

    // 7. BUSCAR CONTEÚDO COMPLETO PARA LEITURA
    public String buscarConteudoFaq(int idArtigo) {
        String sql = "SELECT Conteudo_Texto FROM FAQ_Artigo WHERE ID_Artigo = ?";
        try (java.sql.Connection conn = ConexaoBD.conectar();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idArtigo);
            try (java.sql.ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Conteudo_Texto");
                }
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao buscar conteúdo do FAQ: " + e.getMessage());
        }
        return "Conteúdo indisponível.";
    }
}