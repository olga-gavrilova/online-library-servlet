package com.gmail.olyagavrilova.onlinelibrary.controller;

import com.gmail.olyagavrilova.onlinelibrary.dao.UserDAO;
import com.gmail.olyagavrilova.onlinelibrary.model.Role;
import com.gmail.olyagavrilova.onlinelibrary.model.User;
import com.gmail.olyagavrilova.onlinelibrary.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registrationServlet")
public class RegistrationServer extends javax.servlet.http.HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role =req.getParameter("role");

        UserService userService = new UserService();


        userService.createUser(login, password,role);
        String destPage = "index.jsp";

        req.setAttribute("newUser", login);

        RequestDispatcher rd = req.getRequestDispatcher(destPage);
        rd.forward(req, resp);


    }
}
