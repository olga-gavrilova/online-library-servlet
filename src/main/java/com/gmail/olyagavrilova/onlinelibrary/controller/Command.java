package com.gmail.olyagavrilova.onlinelibrary.controller;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
