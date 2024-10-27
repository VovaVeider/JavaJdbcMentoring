package org.example.demo1.utils;


import org.example.demo1.exeptions.DatabaseManagerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;
    private final String url;
    private final String login;
    private final String password;

    public DatabaseManager(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public void openConnection() {
        if (!isOpenConnection())
            try {
                connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException e) {
                throw new DatabaseManagerException(e);
            }
    }

    public Connection getConnection() {
        if (!isOpenConnection())
            throw new DatabaseManagerException("Ошибка, нет открытого подключения.");
        return connection;
    }

    public boolean closeConnection() {
        try {
            if (isOpenConnection()) {
                connection.close();
                connection = null;
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DatabaseManagerException(e);
        }

    }

    public boolean isOpenConnection() {
        try {
            if (connection == null || connection.isClosed())
                return false;
        } catch (SQLException e) {
            return false;
        }
        return true;
    }


}
