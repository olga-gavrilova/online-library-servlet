package com.gmail.olyagavrilova.onlinelibrary.service;

import com.gmail.olyagavrilova.onlinelibrary.dao.UserDAO;
import com.gmail.olyagavrilova.onlinelibrary.dao.dto.UserDto;
import com.gmail.olyagavrilova.onlinelibrary.exception.UserNotFoundException;
import com.gmail.olyagavrilova.onlinelibrary.model.Role;
import com.gmail.olyagavrilova.onlinelibrary.model.User;

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



public class UserService  {

    private final SecureRandom random = new SecureRandom();

  private UserDAO userDAO;
    private static Logger logger = Logger.getLogger("UserService");


    public User loadUserByUsername(String username) {
        return userDAO.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
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



    public void createUser(String username, String password, String role)  {
        UserDto userDto= new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(getHashFromPassword(password));
        userDto.setRole(Role.valueOf(role));
        userDto.setEnabled(true);

        userDAO.addUserToBD(userDto);
    }


    public List<UserDto> findAllReaders() {
        List<User> list= userDAO.findAll();
        List<UserDto> userDtoList= new ArrayList<>();

        for(User user: list){
            UserDto userDto= new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUserName());
            userDto.setEnabled(user.isEnabled());
            userDtoList.add(userDto);
        }

        return userDtoList;
    }


    public void changeUsersEnable(int id) {
        User user = userDAO.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such id was not found"));

        if (user.isEnabled()) {
            user.setEnabled(false);

        } else {
            user.setEnabled(true);
        }

        userDAO.updateUsersEnable(id, user.isEnabled());
    }

}
