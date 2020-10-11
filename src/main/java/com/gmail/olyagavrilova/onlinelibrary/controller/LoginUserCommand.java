package com.gmail.olyagavrilova.onlinelibrary.controller;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.User;
import com.gmail.olyagavrilova.onlinelibrary.service.UserService;
import com.mysql.cj.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;


public class LoginUserCommand implements Command {
    private UserService userService ;
    private static Logger logger = Logger.getLogger("UserCommand");

    public LoginUserCommand(UserService userService) {
        this.userService= userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if( StringUtils.isNullOrEmpty(userName) || StringUtils.isNullOrEmpty(password) ){
            return "/login.jsp";
        }

        User user = userService.loadUserByUsername(userName);

        if (user.getPassword().equals(userService.getHashFromPassword(password))) {
            request.getSession().setAttribute("user", user);
            logger.info("User " + userName + " logged successfully.");
            return "/WEB-INF/studentlist.jsp";

        }
        logger.info("Invalid attempt of login user:'"+ userName+"'");
        return "/login.jsp";
    }


}
