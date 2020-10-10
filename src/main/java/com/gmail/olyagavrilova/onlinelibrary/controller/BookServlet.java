package com.gmail.olyagavrilova.onlinelibrary.controller;

import com.gmail.olyagavrilova.onlinelibrary.dao.BookDAO;
import com.gmail.olyagavrilova.onlinelibrary.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookServlet")
public class BookServlet extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.findById(Integer.parseInt(bookId));

        req.setAttribute("book", book);

        RequestDispatcher rd = req.getRequestDispatcher("books.jsp");
        rd.forward(req, resp);
    }
}
