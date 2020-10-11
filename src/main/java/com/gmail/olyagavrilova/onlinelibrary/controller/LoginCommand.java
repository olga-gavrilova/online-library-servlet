package com.gmail.olyagavrilova.onlinelibrary.controller;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.User;
import com.gmail.olyagavrilova.onlinelibrary.service.BookService;
import com.gmail.olyagavrilova.onlinelibrary.service.UserService;
import com.mysql.cj.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {

    private final UserService userService;
    private final BookService bookService;

    public LoginCommand() {
        this.userService = new UserService();
        this.bookService = new BookService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if( StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password) ){
            return "/index.jsp";
        }

        User user = userService.loadUserByUsername(userName);

        if (user.getPassword().equals(userService.encryptPassword(password))) {
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("books", bookService.findAllBooks());
            return "/books.jsp";
        }

        return "/index.jsp";
    }
}
