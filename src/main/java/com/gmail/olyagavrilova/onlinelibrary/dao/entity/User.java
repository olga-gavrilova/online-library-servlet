package com.gmail.olyagavrilova.onlinelibrary.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String username;
    private String password;
    private Role role;
    private boolean enabled = true;
    private List<Book> userBooks = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, Role role, boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public User(int id, String username, String password, Role role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Book> getUserBooks() {
        return userBooks;
    }

    public void setUserBooks(List<Book> userBooks) {
        this.userBooks = userBooks;
    }

    @Override
    public String toString() {
        return "User{" + System.identityHashCode(this)+
                "id=" + id +
                ", userName='" + username + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }

}
