package com.gmail.olyagavrilova.onlinelibrary.dao;

import com.gmail.olyagavrilova.onlinelibrary.dao.entity.Subscription;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubscriptionDAO {
    private final static Logger logger = Logger.getLogger("SubscriptionDAO");

    public void create(Subscription subscription){
        try (Connection connection = DataSource.getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement("INSERT INTO subscription (user_id, " +
                     " book_id) " +
                     "VALUES (?,?)")) {

            insertPreparedStatement.setInt(1, subscription.getUserId());
            insertPreparedStatement.setInt(2, subscription.getBookId());

            insertPreparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception during inserting subscription into DB", e);
        }
    }

    public void delete(int id) {
        if(id <= 0){
            logger.log(Level.INFO, "There ia no such Subscription in DB");
        }else {
            try (Connection connection = DataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("delete from subscription where id =  ?")) {

                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Exception during deleting subscription", e);
            }
        }
    }
}
