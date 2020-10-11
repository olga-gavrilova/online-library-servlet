package com.gmail.olyagavrilova.onlinelibrary.dao;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDAO {
    private final static Logger logger = Logger.getLogger("BookDAO");

    public void create(Book book) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement("INSERT INTO books (title, " +
                     "author,publisher,quantity, year_of_publishing ) " +
                     "VALUES (?,?,?,?,?)");
             Statement statement = connection.createStatement()) {

            insertPreparedStatement.setString(1, book.getTitle());
            insertPreparedStatement.setString(2, book.getAuthor());
            insertPreparedStatement.setString(3, book.getPublisher());
            insertPreparedStatement.setInt(4, book.getQuantity());
            insertPreparedStatement.setInt(5, book.getYearOfPublishing());

            insertPreparedStatement.executeUpdate();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE title = '" + book.getTitle() +
                    "'" + "AND author = '" + book.getAuthor() + "'" + "AND publisher = '" + book.getPublisher()
                    + "'" + "AND year_of_publishing = '" + book.getYearOfPublishing() + "'")) {
                resultSet.next();
                book.setId(resultSet.getInt("book_id"));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during inserting book into db", e);
        }
    }

    public void update(Book book) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Update books" +
                     " Set title = (?), author = (?), publisher = (?), quantity = (?),  year_of_publishing = (?)" +
                     " where id = (?)")) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getYearOfPublishing());
            preparedStatement.setInt(6, book.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during updating book", e);
        }
    }

    public void deleteBook(int id) {
        if (id <= 0) {
            logger.log(Level.INFO, "There ia no such a Book in DB");
        } else {
            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("delete from books where id =  (?)")) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Exception during deleting book", e);
            }
        }
    }

    public List<Book> findAllBooksForUser(String username) {
        List<Book> listOfBooks = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select distinct books.id, books.title, books.author, books.publisher from books " +
                     "inner join subscription " +
                     "on books.id = subscription.book_id " +
                     "inner join users " +
                     "on subscription.user_id = users.id " +
                     "and users.username = '" + username + "'")) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getNString("title"));
                book.setAuthor(resultSet.getNString("author"));
                book.setPublisher(resultSet.getNString("publisher"));

                listOfBooks.add(book);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during fetching the book from db", e);
        }

        return listOfBooks;
    }

    public Optional<Book> findById(int id) {
        Book book = null;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from books where id = '" + id + "'")) {

            resultSet.next();

            book = new Book(resultSet.getInt("id"),
                    resultSet.getNString("title"),
                    resultSet.getNString("author"),
                    resultSet.getNString("publisher"),
                    resultSet.getInt("quantity"),
                    resultSet.getInt("year_of_publishing"));


        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting the book from db", e);
        }
        return Optional.ofNullable(book);
    }

    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from books where title = '" + title + "'")) {

            resultSet.next();

            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getNString("title"),
                        resultSet.getNString("author"),
                        resultSet.getNString("publisher"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("year_of_publishing"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting the book from db", e);
        }
        return books;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from books where author = '" + author + "'")) {

            resultSet.next();

            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getNString("title"),
                        resultSet.getNString("author"),
                        resultSet.getNString("publisher"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("year_of_publishing"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting the book from db", e);
        }
        return books;
    }

    public List<Book> findByPublisher(String publisher) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from books where publisher = '" + publisher + "'")) {

            resultSet.next();

            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getNString("title"),
                        resultSet.getNString("author"),
                        resultSet.getNString("publisher"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("year_of_publishing"));

                books.add(book);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting the book from db", e);
        }
        return books;
    }

    public List<Book> findAll() {
        List<Book> listOfBooks = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {

            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt("id"),
                        resultSet.getNString("title"),
                        resultSet.getNString("author"),
                        resultSet.getNString("publisher"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("year_of_publishing"));

                listOfBooks.add(book);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during fetching the book from db", e);
        }
        return listOfBooks;
    }

    public List<Book> findAllBooksWithPagination(int start, int total) {
        List<Book> list = findAll();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from books limit " + (start - 1) + ", " + total)) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getNString("title"));
                book.setAuthor(resultSet.getNString("author"));
                book.setPublisher(resultSet.getNString("publisher"));
                book.setQuantity(resultSet.getInt("quantity"));
                book.setYearOfPublishing(resultSet.getInt("year_of_publishing"));
                list.add(book);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during finding books with pagination", e);
        }
        return list;
    }
}
