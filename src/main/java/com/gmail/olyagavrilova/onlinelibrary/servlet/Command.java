package com.gmail.olyagavrilova.onlinelibrary.servlet;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
