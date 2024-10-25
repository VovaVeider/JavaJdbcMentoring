package org.example.demo1;

import org.example.demo1.config.AppConfig;
import org.example.demo1.entity.Book;
import org.example.demo1.repository.BookRepository;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var cm = AppConfig.getConnectionManager();
        var br = new BookRepository(cm);
        System.out.println("aaaaaaaaaaa");
        try {
            cm.getConnection().setAutoCommit(false);
            var book = new Book("666", "111");
            br.saveBook(book);
            System.out.println(book.getId());
            System.out.println("aaaa");
            cm.getConnection().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (cm.isOpen()) {
                cm.getConnection().rollback();
            }

        } finally {
            if (cm.isOpen()) {
                cm.getConnection().close();
            }
        }
    }
}
