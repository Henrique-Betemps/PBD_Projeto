package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/ufpel_resolve";
    private static final String USER = "usuario";
    private static final String PASSWORD = "usuario";
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}