package com.gmail.olyagavrilova.onlinelibrary.model;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Role;

public class UserDto {
    private long id;
    private String username;
    private  String  password;
    private Role role;
    private boolean enabled;

    public UserDto(long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password=password;
        this.role=role;
    }

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String  getPassword() {
        return password;
    }

    public void setPassword(String  password) {
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
}
