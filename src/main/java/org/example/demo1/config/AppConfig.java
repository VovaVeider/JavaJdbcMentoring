package org.example.demo1.config;

public final class AppConfig {
    private static final String dbUrl = "jdbc:postgresql://localhost:5432/library_db";
    private static final String dbLogin = "admin";
    private static final String dbPassword = "hello88sharky";

    private static ConnectionManager cm;
    public static ConnectionManager getConnectionManager() {
        if(cm == null) {
            cm = new ConnectionManager(dbUrl, dbLogin, dbPassword);
        }
        return cm;
    }
}

