package com.gmail.olyagavrilova.onlinelibrary.service;

import com.gmail.olyagavrilova.onlinelibrary.dao.BookDAO;
import com.gmail.olyagavrilova.onlinelibrary.dao.SubscriptionDao;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Book;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Subscription;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookService {
    private static Logger logger = Logger.getLogger("BookService");

    private BookDAO bookDAO;
    private UserService userService;
    private SubscriptionDao subscriptionDao;

    public void addBookToSubscriptionForUser(int bookId) {
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        User user = userService.loadUserByUsername(userName);

        Book book = bookDAO.findById(bookId);

        Subscription subscription = new Subscription();
        subscription.setBookId(bookId);
//        subscription.setUserId(user.getId());


        subscriptionDao.create(subscription);

        book.setQuantity(book.getQuantity() - 1);
        bookDAO.update(book);

        logger.log(Level.INFO, "New book  was added to subscription");
    }


    public void removeBookFromSubscriptionForUser(int bookId, int userId) {
        List<Subscription> subscriptionList = subscriptionDao.findByUserId(userId);

        Book book = bookDAO.findById(bookId);

        for (Subscription subscription : subscriptionList) {
            if (subscription.getBookId() == bookId ) {
                subscriptionDao.deleteSubscription(subscription);
                break;
            }
        }
        book.setQuantity(book.getQuantity() + 1);

        logger.log(Level.INFO, "Book  was returned to the library from the subscription");

        bookDAO.update(book);
    }
}
