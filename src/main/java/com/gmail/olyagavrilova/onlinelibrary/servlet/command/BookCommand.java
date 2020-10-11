package com.gmail.olyagavrilova.onlinelibrary.servlet.command;

import com.gmail.olyagavrilova.onlinelibrary.service.BookService;
import com.gmail.olyagavrilova.onlinelibrary.servlet.Command;

import javax.servlet.http.HttpServletRequest;

public class BookCommand implements Command {

    private final BookService bookService;

    public BookCommand() {
        this.bookService = new BookService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String quantity = request.getParameter("quantity");
        String yearOfPublishing = request.getParameter("yearOfPublishing");

        bookService.createBook(title, author, publisher, quantity, yearOfPublishing);

        return "/books.jsp";
    }
}
