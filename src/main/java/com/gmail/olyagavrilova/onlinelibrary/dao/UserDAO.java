package com.gmail.olyagavrilova.onlinelibrary.dao;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Role;
import com.gmail.olyagavrilova.onlinelibrary.dao.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private final static Logger logger = Logger.getLogger("UserDAO");

    public void create(User user) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement("INSERT INTO users ( " +
                     "username, password, role, enabled) " +
                     "VALUES (?,?,?,?)");
             Statement statement = connection.createStatement()) {

            insertPreparedStatement.setString(1, user.getUserName());
            insertPreparedStatement.setString(2, user.getPassword());
            insertPreparedStatement.setString(3, user.getRole().toString());
            insertPreparedStatement.setBoolean(4, user.isEnabled());

            insertPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during inserting users into db", e);
        }
    }

    public Optional<User> checkLogin(String login, String password) {
        User user = null;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '" + login +
                     "'" + "AND password = '" + password + "'")) {

            resultSet.next();

            user = new User(resultSet.getInt("id"),
                    resultSet.getNString("username"),
                    resultSet.getNString("password"),
                    Role.valueOf(resultSet.getNString("role")),
                    resultSet.getBoolean("enabled"));

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting user from db", e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findByUsername(String username) {
        User user = null;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users where username = '" + username + "'")) {

            resultSet.next();

            user = new User(resultSet.getInt("id"),
                    resultSet.getNString("username"),
                    resultSet.getNString("password"),
                    Role.valueOf(resultSet.getNString("role")),
                    resultSet.getBoolean("enabled"));


        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting user from db", e);
        }
        return Optional.ofNullable(user);
    }

    public Optional<User> findById(int id) {
        User user = null;
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users where id = '" + id + "'")) {

            resultSet.next();

            user = new User(resultSet.getInt("id"),
                    resultSet.getNString("username"),
                    resultSet.getNString("password"),
                    Role.valueOf(resultSet.getNString("role")),
                    resultSet.getBoolean("enabled"));


        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting user from db", e);
        }
        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        List<User> listOfUsers = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getNString("username"),
                        resultSet.getNString("password"),
                        Role.valueOf(resultSet.getNString("role")),
                        resultSet.getBoolean("enabled"));

                listOfUsers.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during fetching the users from db", e);
        }
        return listOfUsers;
    }

    public void updateUsersEnable(int userId, boolean enabled) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("Update users" +
                     " Set enabled = ? " +
                     " where id = ?")) {

            preparedStatement.setBoolean(1, enabled);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during updating book", e);
        }
    }
}
