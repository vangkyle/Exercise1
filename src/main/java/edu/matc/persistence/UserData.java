package edu.matc.persistence;

import edu.matc.entity.User;
import org.apache.log4j.*;

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
     * String query to get all users.
     *
     * @return executeQuery(sql)
     */
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM users";
        return executeQuery(sql);
    }

    /**
     * String query to get users by last name.
     *
     * @param lastName last name
     * @return executeQuery(sql)
     */
    public List<User> getByLastName(String lastName) {
        String sql = "SELECT * FROM users WHERE last_name LIKE '%" + lastName + "%'";
        return executeQuery(sql);
    }


    /**
     * Execute queries.
     *
     * @param sql sql
     * @return the users
     */
    public List<User> executeQuery(String sql) {

        logger.info("In executeQuery");
        logger.error("Demonstrating error level");
        logger.trace("Trace Message!");
        logger.debug("Debug Message!");
        logger.warn("Warn Message!");
        logger.fatal("Fatal Message!");


        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;

        try {

            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            demoWhile(users, results);
            database.disconnect();

        } catch (SQLException e) {

            logger.error("SQL Exception... ", e);
            //System.out.println("SearchUser.getByLastName()...SQL Exception: " + e);

        } catch (Exception e) {

            logger.info("Exception... ", e);
            //System.out.println("SearchUser.getByLastName()...Exception: " + e);

        }

        return users;
    }


    private void demoWhile(List<User> users, ResultSet results) throws SQLException {
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