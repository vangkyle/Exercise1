package edu.matc.persistence;

import edu.matc.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());


    /**
     * Gets all users through sql query string.
     *
     * @return the all users
     */
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return executeQuery(sql);

    }

    /**
     * Gets user by last name through sql query string.
     *
     * @param lastName the last name
     * @return the user by last name
     */
    public List<User> getUserByLastName(String lastName) {
        String sql = "SELECT * FROM users WHERE last_name LIKE '%" + lastName + "%'";
        return executeQuery(sql);
    }

    /**
     * Execute query list.
     *
     * @param sql the sql
     * @return the list
     */
    public List<User> executeQuery(String sql) {

        logger.info("info message");
        logger.trace("trace message");
        logger.debug("debug message");
        logger.error("error message");
        logger.fatal("fatal message");

        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            doWhile(users, results);
            database.disconnect();
        } catch (SQLException e) {
            logger.info("SearchUser.getAllUsers()...SQL Exception: " + e);
            logger.error("SQL Exception: " + e);
            //System.out.println("SearchUser.getAllUsers()...SQL Exception: " + e);
        } catch (Exception e) {
            logger.info("SearchUser.getAllUsers()...Exception: " + e);
            logger.error("Exception: " + e);
            //System.out.println("SearchUser.getAllUsers()...Exception: " + e);
        }
        return users;
    }


    private void doWhile(List<User> users, ResultSet results) throws SQLException {
        while (results.next()) {
            User employee = createUserFromResults(results);
            users.add(employee);
        }
    }


    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setFirstName(results.getString("first_name"));
        user.setUserid(results.getString("id"));
        user.setDateOfBirth(results.getDate("date_of_birth").toLocalDate());
        return user;
    }

}