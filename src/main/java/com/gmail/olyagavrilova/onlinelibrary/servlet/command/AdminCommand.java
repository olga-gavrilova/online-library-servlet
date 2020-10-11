package com.gmail.olyagavrilova.onlinelibrary.servlet.command;

import com.gmail.olyagavrilova.onlinelibrary.service.UserService;
import com.gmail.olyagavrilova.onlinelibrary.servlet.Command;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements Command {

    private final UserService userService;

    public AdminCommand() {
        this.userService = new UserService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute("books", userService.findAllReaders());

        return "/users.jsp";
    }
}
