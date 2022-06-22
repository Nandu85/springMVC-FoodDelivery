package com.narola.fooddelivery.user;

import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.restaurants.dao.RestDAOMYSQL;
import com.narola.fooddelivery.utility.DAOFactory;
import com.narola.fooddelivery.utility.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    @Autowired
    RestDAOMYSQL restDao;

    public static User addUser(User user) throws DatabaseException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = con.prepareStatement(
                    "INSERT INTO `user`\r\n"
                            + "(`username`, `email`,\r\n"
                            + "`password`, `usertype`)\r\n"
                            + "VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getAdmin());

            ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                user.setUserId(resultSet.getInt(1));

            }

        } catch (SQLException e) {
            throw new DatabaseException("Error while insert user", e);
        } catch (DatabaseException e) {
            throw e;
        } finally {
            DBConnection.releaseResource(ps, resultSet);
        }
        return user;
    }


    public static User findUser(String username, String password) throws DatabaseException {

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = new User();

        try {

            String query = "SELECT * FROM `user` where username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            resultSet = ps.executeQuery();


            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(username);
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getInt("usertype"));
                if (user.getAdmin() == 3)
                    user.setRestaurantId(DAOFactory.getInstance().getRestDAO().getRestaurantFromUserId(resultSet.getInt("userId")).getRestaurantId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while findUser", e);
        } catch (DatabaseException e) {
            throw e;
        } finally {
            DBConnection.releaseResource(ps, resultSet);
        }
        if (user.getUserId() != 0) {
            if (!user.getPassword().equals(password)) {
                throw new UserException("Password is Incorrect");
            }
            return user;
        } else {
            throw new UserException("User Not Found");
        }


    }

    public static User updateUser(User user) throws DatabaseException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = con.prepareStatement("UPDATE `user`\r\n"
                    + "SET `username` = ?, `email` = ?,\r\n"
                    + "`password` = ?, `usertype` = ?\r\n"
                    + "WHERE `userId` = ?");

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getAdmin());
            ps.setInt(5, user.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error while updateUser", e);
        } catch (DatabaseException e) {
            throw e;
        } finally {
            DBConnection.releaseResource(ps, resultSet);
        }

        return user;
    }

    public static User DeleteUser(User user) throws DatabaseException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = con.prepareStatement("DELETE FROM user WHERE userId=?");
            ps.setString(1, String.valueOf(user.getUserId()));
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error while DeleteUser", e);
        } catch (DatabaseException e) {
            throw e;
        } finally {
            DBConnection.releaseResource(ps, resultSet);
        }
        return user;

    }

    public List<User> findAllUser() throws DatabaseException {

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> users = null;

        try {
            users = new ArrayList<>();
            String query = "SELECT * FROM `user`";
            ps = con.prepareStatement(query);
            resultSet = ps.executeQuery();


            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getInt("usertype"));
                if (user.getAdmin() == 3)
                    user.setRestaurantId(DAOFactory.getInstance().getRestDAO().getRestaurantFromUserId(resultSet.getInt("userId")).getRestaurantId());

                users.add(user);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while findAllUser", e);
        } catch (DatabaseException e) {
            throw e;
        } finally {
            DBConnection.releaseResource(ps, resultSet);
        }
        return users;


    }

    public static User findByUserId(int id) throws DatabaseException {

        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = new User();

        try {

            String query = "SELECT * FROM `user` where userId=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            resultSet = ps.executeQuery();


            if (resultSet.next()) {

                user.setUserId(resultSet.getInt("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getInt("usertype"));
                user.setRestaurantId(DAOFactory.getInstance().getRestDAO().getRestaurantFromUserId(id).getRestaurantId());
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error while findByUserId", e);
        } catch (DatabaseException e) {
            throw e;
        } finally {
            DBConnection.releaseResource(ps, resultSet);
        }
        return user;


    }


}
