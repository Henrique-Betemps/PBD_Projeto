package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal {
    public static void main(String[] args) {
        // Instancia o gestor do banco de dados
        Gestor_Solicitacao gestor = new Gestor_Solicitacao();

        // Configuração da janela principal
        JFrame frame = new JFrame("UFPel Resolve - Sistema de Atendimento");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cria o sistema de abas
        JTabbedPane abas = new JTabbedPane();

        // ==========================================
        // ABA 1: NOVA SOLICITAÇÃO (Inserção)
        // ==========================================
        JPanel painelNova = new JPanel(null);

        JLabel lblDescricao = new JLabel("Descrição do Problema:");
        lblDescricao.setBounds(20, 20, 150, 25);
        JTextField txtDescricao = new JTextField();
        txtDescricao.setBounds(180, 20, 300, 25);

        JLabel lblMatricula = new JLabel("Matrícula do Aluno:");
        lblMatricula.setBounds(20, 60, 150, 25);
        JTextField txtMatricula = new JTextField();
        txtMatricula.setBounds(180, 60, 150, 25);

        JLabel lblCategoria = new JLabel("ID da Categoria (Setor):");
        lblCategoria.setBounds(20, 100, 150, 25);
        JTextField txtCategoria = new JTextField();
        txtCategoria.setBounds(180, 100, 150, 25);

        JButton btnSalvar = new JButton("Abrir Solicitação");
        btnSalvar.setBounds(180, 150, 150, 35);

        // Ação do botão salvar
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String desc = txtDescricao.getText();
                    String mat = txtMatricula.getText();
                    int cat = Integer.parseInt(txtCategoria.getText());

                    gestor.abrirSolicitacao(desc, mat, cat);
                    JOptionPane.showMessageDialog(frame, "Solicitação registrada com sucesso!");

                    // Limpa os campos após salvar
                    txtDescricao.setText("");
                    txtMatricula.setText("");
                    txtCategoria.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: O ID da Categoria deve ser um número inteiro.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        painelNova.add(lblDescricao);
        painelNova.add(txtDescricao);
        painelNova.add(lblMatricula);
        painelNova.add(txtMatricula);
        painelNova.add(lblCategoria);
        painelNova.add(txtCategoria);
        painelNova.add(btnSalvar);
        abas.addTab("Nova Solicitação", painelNova);

        // ==========================================
        // ABA 2: ATUALIZAR STATUS (Atualização)
        // ==========================================
        JPanel painelAtualizar = new JPanel(null);

        JLabel lblProtocolo = new JLabel("Número do Protocolo:");
        lblProtocolo.setBounds(20, 40, 150, 25);
        JTextField txtProtocolo = new JTextField();
        txtProtocolo.setBounds(180, 40, 100, 25);

        JButton btnResolver = new JButton("Marcar como 'Resolvida'");
        btnResolver.setBounds(180, 90, 200, 35);

        // Ação do botão atualizar
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int prot = Integer.parseInt(txtProtocolo.getText());
                    gestor.atualizarStatus(prot, "Resolvida");

                    // Nota: O gatilho (trigger) no Postgres vai preencher a data de fechamento automaticamente!
                    JOptionPane.showMessageDialog(frame, "Status atualizado! O gatilho do banco gerou a data de fechamento.");
                    txtProtocolo.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: Informe um número de protocolo válido.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        painelAtualizar.add(lblProtocolo);
        painelAtualizar.add(txtProtocolo);
        painelAtualizar.add(btnResolver);
        abas.addTab("Atualizar Chamado", painelAtualizar);

        // ==========================================
        // ABA 3: CONSULTAR CHAMADOS (Leitura)
        // ==========================================
        JPanel painelConsultar = new JPanel(new BorderLayout());

        JButton btnRecarregar = new JButton("Recarregar Dados do Banco");
        JTable tabela = new JTable(); // Tabela visual vazia
        JScrollPane scrollPane = new JScrollPane(tabela); // Adiciona barra de rolagem na tabela

        // Ação do botão recarregar
        btnRecarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama o novo método para buscar os dados e injeta na tabela
                tabela.setModel(gestor.listarParaTabela());
            }
        });

        painelConsultar.add(btnRecarregar, BorderLayout.NORTH);
        painelConsultar.add(scrollPane, BorderLayout.CENTER);
        abas.addTab("Consultar Registros", painelConsultar);

        // Adiciona as abas na janela e exibe
        frame.add(abas);
        frame.setVisible(true);
    }
}