package com.gmail.olyagavrilova.onlinelibrary.dao;

import com.gmail.olyagavrilova.onlinelibrary.model.Book;
import com.gmail.olyagavrilova.onlinelibrary.model.Subscription;
import com.gmail.olyagavrilova.onlinelibrary.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriptionDao {
    private static Logger logger = Logger.getLogger("SubscriptionDAO");

    public void create(Subscription subscription){
        try (Connection connection = DataSource.getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement("INSERT INTO subscription (user_id, " +
                     " book_id) " +
                     "VALUES (?,?)");
             Statement statement = connection.createStatement()) {

            insertPreparedStatement.setInt(1, subscription.getUserId());
            insertPreparedStatement.setInt(2, subscription.getBookId());

            insertPreparedStatement.executeUpdate();

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM subscription WHERE user_id= '"
                    + subscription.getUserId()+"'" + "AND book_id = '"+ subscription.getBookId()+"'")) {
                resultSet.next();
                subscription.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during inserting subscription into DB", e);
        }
    }

    public List<Subscription> findByUserId(int userId){
        List<Subscription> subscriptionList = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from subscription where user_id =" + userId )) {

            while (resultSet.next()){
                Subscription subscription  = new Subscription(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"));
                subscriptionList.add(subscription);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during getting the subscription from db", e);
        }
        return subscriptionList;

    }

    public List<Subscription> findAll(){
        List<Subscription> listOfSubscriptions = new ArrayList<>();

        try (Connection connection = DataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM subscription")) {

            while (resultSet.next()) {
                Subscription subscription = new Subscription(resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"));

                listOfSubscriptions.add(subscription);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during fetching all subscriptions from db", e);
        }
        return listOfSubscriptions;
    }

    public void deleteSubscription(Subscription subscription) {

        if(subscription.getId() == 0){
            logger.log(Level.INFO, "There ia no such Subscription in DB");
        }else {
            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("delete from subscription where id =  ?")) {
                preparedStatement.setInt(1, subscription.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Exception during deleting subscription", e);
            }
        }
    }
}
