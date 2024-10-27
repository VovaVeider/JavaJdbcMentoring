package org.example.demo1.reader.repository;

import org.example.demo1.utils.DatabaseManager;

public class ReaderRepository {
    private final DatabaseManager databaseManager;
    public ReaderRepository(DatabaseManager databaseManager) {
            this.databaseManager = databaseManager;
    }
}
