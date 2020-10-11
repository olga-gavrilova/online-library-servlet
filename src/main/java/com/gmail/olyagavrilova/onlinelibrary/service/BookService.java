package com.gmail.olyagavrilova.onlinelibrary.service;

import com.gmail.olyagavrilova.onlinelibrary.dao.BookDAO;
import com.gmail.olyagavrilova.onlinelibrary.dao.SubscriptionDAO;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Book;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Subscription;
import com.gmail.olyagavrilova.onlinelibrary.model.BookDto;
import com.gmail.olyagavrilova.onlinelibrary.service.mapper.BookMapper;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BookService {
    private final static Logger logger = Logger.getLogger("BookService");

    private final BookDAO bookDAO;
    private final SubscriptionDAO subscriptionDao;
    private final BookMapper bookMapper;

    public BookService() {
        this.bookDAO = new BookDAO();
        this.subscriptionDao = new SubscriptionDAO();
        this.bookMapper = new BookMapper();
    }

    public void createBook(String bookTitle, String bookAuthor, String bookPublisher, String bookQuantity, String bookYearOfPublishing) {
        Book book = new Book();

        book.setTitle(bookTitle);
        book.setAuthor(bookAuthor);
        book.setPublisher(bookPublisher);
        book.setQuantity(Integer.parseInt(bookQuantity));
        book.setYearOfPublishing(Integer.parseInt(bookYearOfPublishing));

        bookDAO.create(book);
    }

    public List<BookDto> findAllBooks() {
        return bookDAO.findAll().stream()
                .map(bookMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksByTitle(String title) {
        return bookDAO.findByTitle(title).stream()
                .map(bookMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksByAuthor(String author) {
        return bookDAO.findByAuthor(author).stream()
                .map(bookMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksByPublisher(String publisher) {
        return bookDAO.findByPublisher(publisher).stream()
                .map(bookMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> findBooksForUser(String username) {
        return bookDAO.findAllBooksForUser(username).stream()
                .map(bookMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public void addBookToSubscriptionForUser(int bookId, int userId) {
        Subscription subscription = new Subscription();

        subscription.setBookId(bookId);
        subscription.setUserId(userId);

        subscriptionDao.create(subscription);
    }

    public void removeBookFromSubscriptionForUser(int subscriptionId) {
        subscriptionDao.delete(subscriptionId);
    }
}
