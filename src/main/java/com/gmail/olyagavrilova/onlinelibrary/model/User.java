package com.gmail.olyagavrilova.onlinelibrary.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private String password;
    private Role role;
    private boolean enabled = true;
    private List<Book> userBooks = new ArrayList<>();

    public User() {
    }

    public User(String userName, String password, Role role, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public User(int id, String userName, String password, Role role, boolean enabled) {
        this.id = id;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                ", userName='" + userName + '\'' +
                ", role=" + role +
                ", enabled=" + enabled +
                '}';
    }

}
