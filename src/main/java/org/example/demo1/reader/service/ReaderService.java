package org.example.demo1.reader.service;

import org.example.demo1.reader.repository.ReaderRepository;
import org.example.demo1.utils.DatabaseManager;

public class ReaderService {
    private final ReaderRepository readerRepository;
    private final DatabaseManager databaseManager;
    public ReaderService(ReaderRepository readerRepository, DatabaseManager databaseManager) {
        this.readerRepository = readerRepository;
        this.databaseManager = databaseManager;
    }
}
