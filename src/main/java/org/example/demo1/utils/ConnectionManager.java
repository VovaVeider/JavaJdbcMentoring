package org.example.demo1.utils;


import java.sql.*;
public class ConnectionManager {
    private Connection connection;
    private String url;
    private String login;
    private String password;

    public ConnectionManager(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Connection getConnection() {
            try {
                if(connection == null || connection.isClosed()) {
                    connection = DriverManager.getConnection(url, login, password);

                }
                return connection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Ошибка при создании соединения!");

            }

    }

    public boolean isOpen () {
            return connection != null;
    }
}
