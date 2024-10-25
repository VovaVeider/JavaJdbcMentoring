package org.example.demo1.repository;

import org.example.demo1.entity.Book;
import org.example.demo1.utils.ConnectionManager;

import java.sql.Date;
import java.sql.SQLException;

public class BookRepository {
    private ConnectionManager cm;
    private static final String saveBookSql =
    """
    INSERT INTO BOOKS(isbn, title, author, published_date)
    values(?, ?, ?, ?)
    """;


    public BookRepository (ConnectionManager cm) {
        this.cm = cm;
    }

    public void saveBook(Book book) throws SQLException {
        var con = cm.getConnection();
        var stmt = con.prepareStatement(saveBookSql);
        stmt.setString(1, book.getIsbn());

        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getAuthor());
        stmt.setDate(4, book.getPublishedDate() == null ? null : Date.valueOf(book.getPublishedDate()));

        int insertedRow = stmt.executeUpdate();
        if (insertedRow > 0) {
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1));
            }

        }
        stmt.close();

    }
}



// delete на примере insertedRow

/*
* ddBook(String title, String author, Date publishedDate, String isbn): Добавление книги в базу данных.
getAllBooks(): Получение списка всех книг.
findBookByTitle(String title): Поиск книги по названию.
deleteBook(int id): Удаление книги по идентификатору.*/