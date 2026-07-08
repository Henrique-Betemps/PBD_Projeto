# UFPel Resolve - Sistema de Atendimento

Este projeto é a entrega final (Etapa 2) da disciplina de Projeto de Banco de Dados do curso de Ciência da Computação da Universidade Federal de Pelotas (UFPel).

O sistema **UFPel Resolve** visa centralizar e gerir as solicitações dos alunos junto aos setores administrativos da universidade (como PRAE, CRA e Coordenações), oferecendo um canal oficial para abertura de chamados, atualização de status e acompanhamento de processos.

## 👥 Integrantes do Grupo
* Henrique Betemps Barbosa
* João Teixeira dos Reis
* Carlos Henrique Guglilhermi de Carvalho

## 🛠️ Tecnologias Utilizadas
* **Banco de Dados:** PostgreSQL
* **Linguagem:** Java (JDK 25)
* **Interface Gráfica:** Java Swing
* **Gerenciador de Dependências:** Maven (Driver JDBC do PostgreSQL)

---

## ⚙️ Como executar o projeto localmente

Para testar a aplicação na sua máquina, siga os passos abaixo para configurar o banco de dados e a conexão com a interface gráfica.

### 1. Preparando o Banco de Dados (PostgreSQL)
1. Certifique-se de ter o PostgreSQL rodando em sua máquina (nativamente ou via Docker) na porta `5432`.
2. Crie um banco de dados vazio (recomendação de nome: `ufpel_resolve`).
3. Abra sua ferramenta de administração de banco de dados preferida (ex: DBeaver, pgAdmin).
4. Localize os scripts SQL enviados junto com as entregas do projeto.
5. Execute os scripts na seguinte ordem para respeitar a integridade referencial:
    * **1º DDL:** Criação das tabelas.
    * **2º DML:** Inserção da massa de dados para testes.
    * **3º Triggers:** Criação da função e do gatilho de fechamento automático de chamados.

### 2. Configurando as Credenciais no Java
Por questões de segurança, as credenciais do banco de dados não estão fixadas no código-fonte. Antes de executar o projeto, configure o usuário e a senha da sua instância local do PostgreSQL:

1. Abra o projeto na sua IDE (recomendado: IntelliJ IDEA).
2. Aguarde o Maven baixar as dependências do arquivo `pom.xml`.
3. Navegue até o arquivo `src/main/java/org/example/ConexaoBD.java`.
4. Altere as variáveis `USER` e `PASSWORD` para as credenciais da sua máquina:
   ```java
   private static final String USER = "seu_usuario_local";
   private static final String PASSWORD = "sua_senha_local";