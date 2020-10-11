package com.gmail.olyagavrilova.onlinelibrary.service;

import com.gmail.olyagavrilova.onlinelibrary.dao.UserDAO;
import com.gmail.olyagavrilova.onlinelibrary.model.UserDto;
import com.gmail.olyagavrilova.onlinelibrary.exception.UserNotFoundException;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Role;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.User;
import com.gmail.olyagavrilova.onlinelibrary.service.mapper.UserMapper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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

    private final UserDAO userDAO = new UserDAO();
    private final UserMapper userMapper = new UserMapper();
    private final SecureRandom random = new SecureRandom();

    public void createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(getHashFromPassword(password));
        user.setRole(Role.valueOf(role));
        user.setEnabled(true);

        userDAO.create(user);
    }

    public String getHashFromPassword(String password) {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        SecretKeyFactory f = null;
        byte[] hash = new byte[0];

        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = f.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // throw new exception
        }

        Base64.Encoder enc = Base64.getEncoder();

        return enc.encodeToString(hash);
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
