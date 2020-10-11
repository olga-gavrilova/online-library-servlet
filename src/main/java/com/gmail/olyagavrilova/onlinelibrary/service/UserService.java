package com.gmail.olyagavrilova.onlinelibrary.service;

import com.gmail.olyagavrilova.onlinelibrary.dao.UserDAO;
import com.gmail.olyagavrilova.onlinelibrary.model.UserDto;
import com.gmail.olyagavrilova.onlinelibrary.exception.UserNotFoundException;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Role;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.User;
import com.gmail.olyagavrilova.onlinelibrary.service.mapper.UserMapper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UserService {

    private static Logger logger = Logger.getLogger("UserService");

    private final UserDAO userDAO;
    private final UserMapper userMapper;
    private final SecureRandom random;

    public UserService() {
        this.userDAO = new UserDAO();
        this.userMapper = new UserMapper();
        this.random = new SecureRandom();
    }

    public void createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword(password));
        user.setRole(Role.valueOf(role));
        user.setEnabled(true);

        userDAO.create(user);
    }

    public String encryptPassword(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {

        }
        return null;
    }

    public User loadUserByUsername(String username) {
        return userDAO.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }

    public List<UserDto> findAllReaders() {
        return userDAO.findAll().stream()
                .map(userMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public void changeUsersEnable(int id) {
        User user = userDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such id was not found"));

        user.setEnabled(!user.isEnabled());

        userDAO.updateUsersEnable(id, user.isEnabled());
    }
}
