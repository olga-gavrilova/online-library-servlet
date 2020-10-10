package com.gmail.olyagavrilova.onlinelibrary.controller;

import com.gmail.olyagavrilova.onlinelibrary.dao.UserDAO;
import com.gmail.olyagavrilova.onlinelibrary.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");

        UserDAO userDao = new UserDAO();


            User user = userDao.checkLogin(login, password);
            String destPage = "index.jsp";

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                destPage = "findbook.jsp";
            } else {
                String message = "Invalid email/password! Try again";
                req.setAttribute("message", message);

            }

            RequestDispatcher dispatcher = req.getRequestDispatcher(destPage);
            dispatcher.forward(req, resp);

    }

}
