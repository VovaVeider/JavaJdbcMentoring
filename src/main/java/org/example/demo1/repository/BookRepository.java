package org.example.demo1.repository;

import org.example.demo1.entity.Book;
import org.example.demo1.config.ConnectionManager;
import org.example.demo1.exeptions.NotUniqueISBNException;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private final ConnectionManager cm;
    private static final String saveBookSql =
    """
    INSERT INTO BOOKS(isbn, title, author, published_date)
    values(?, ?, ?, ?)
    """;
private static final String findByIsbn = "SELECT 1 FROM BOOKS WHERE isbn = ?";

    public BookRepository (ConnectionManager cm) {
        this.cm = cm;
    }

    public Book saveBook(Book book) throws NotUniqueISBNException {
        try (var connection = cm.getConnection();
             var findByIsbnStatement = connection.prepareStatement(findByIsbn);
             var saveStatement = connection.prepareStatement(saveBookSql)
        ) {
            findByIsbnStatement.setString(1, book.getIsbn());
            ResultSet findByIsbnRs = findByIsbnStatement.executeQuery();
            if (findByIsbnRs.next()) {
                throw new NotUniqueISBNException("ISBN not unique: " + book.getIsbn());
            }

            saveStatement.setString(1, book.getIsbn());
            saveStatement.setString(2, book.getTitle());
            saveStatement.setString(3, book.getAuthor());
            saveStatement.setDate(4, book.getPublishedDate() == null ? null : Date.valueOf(book.getPublishedDate()));

            int insertedRow = saveStatement.executeUpdate();
            if (insertedRow > 0) {
                var rs = saveStatement.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getInt(1));
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = cm.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM BOOKS")
        ) {

            while (rs.next()) {
              books.add(new Book(
                      rs.getInt("id"),
                      rs.getString("isbn"),
                      rs.getString("title"),
                      rs.getString("author"),
                      rs.getDate("published_date") == null
                              ? null
                              : LocalDate.ofInstant(rs.getDate("published_date").toInstant(), ZoneId.systemDefault())
              ));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return books;
    }
}



// delete на примере insertedRow

//рповерка уникальности String isbn
//даты
//листы
//try-with-resource