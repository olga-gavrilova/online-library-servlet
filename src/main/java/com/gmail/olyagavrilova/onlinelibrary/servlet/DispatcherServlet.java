package com.gmail.olyagavrilova.onlinelibrary.servlet;

import com.gmail.olyagavrilova.onlinelibrary.servlet.command.AdminCommand;
import com.gmail.olyagavrilova.onlinelibrary.servlet.command.BookCommand;
import com.gmail.olyagavrilova.onlinelibrary.servlet.command.LoginCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    public void init() {
        commands.put("login", new LoginCommand());
        commands.put("admin", new AdminCommand());
        commands.put("book", new BookCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String path = request.getRequestURI();
        path = path.replaceAll(".*/api/", "");

        Command command = commands.getOrDefault(path, (r) -> "/index.jsp)");
        String page = command.execute(request);
        request.getRequestDispatcher(page).forward(request, response);
    }
}
