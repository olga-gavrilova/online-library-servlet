package com.gmail.olyagavrilova.onlinelibrary.dao;

import com.gmail.olyagavrilova.onlinelibrary.service.BookService;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends javax.servlet.http.HttpServlet {

    BookService bookService;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter printWriter=response.getWriter();
        printWriter.println("<html>");
        printWriter.println("<h1> Hello World servlet! </h1>");
        printWriter.println("</html>");
        //bookService
    }
}
