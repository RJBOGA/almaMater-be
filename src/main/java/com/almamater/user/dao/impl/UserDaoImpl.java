package com.almamater.user.dao.impl;

import com.almamater.user.dao.UserDao;
import com.almamater.user.entities.User;
import com.almamater.user.utils.ConnectionException;
import com.almamater.user.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String jdbcusername = "root";
    private static final String jdbcpassword = "";

    @Override
    public boolean addUser(User user) {
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

            String query = "INSERT INTO users (studentId, email, password, firstName, lastName, department, graduationYear, profession, contactNumber, dateTime, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getStudentId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getDepartment());
            preparedStatement.setInt(7, user.getGraduationYear());
            preparedStatement.setString(8, user.getProfession());
            preparedStatement.setString(9, user.getContactNumber());
            preparedStatement.setObject(10, LocalDateTime.now());
            preparedStatement.setString(11, user.getAddress());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows);
            return rows > 0;

        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByUsernameAndPassword(int studentId, String password) {
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword)) {

            String query = "SELECT * FROM users WHERE studentId = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }

        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllAlumni() {
        List<User> alumniList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User alumni = new User();
                alumni.setStudentId(resultSet.getInt("studentId"));
                alumni.setFirstName(resultSet.getString("firstName"));
                alumni.setLastName(resultSet.getString("lastName"));
                alumni.setEmail(resultSet.getString("email"));
                alumni.setProfession(resultSet.getString("profession"));
                alumniList.add(alumni);
            }
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }

        return alumniList;
    }

    @Override
    public User getUserByStudentId(int studentId) {
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword)) {

            String query = "SELECT * FROM users WHERE studentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }

        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        boolean success = false;
        String sql = "UPDATE users SET email=?, firstName=?, lastName=?, department=?, graduationYear=?, profession=?, contactNumber=?, address=? WHERE studentId=?";

        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getDepartment());
            statement.setInt(5, user.getGraduationYear());
            statement.setString(6, user.getProfession());
            statement.setString(7, user.getContactNumber());
            statement.setString(8, user.getAddress());
            statement.setInt(9, user.getStudentId());

            int rowsUpdated = statement.executeUpdate();
            success = (rowsUpdated > 0);
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteUserById(int userId) {
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword);
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE studentId = ?")) {
            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setStudentId(resultSet.getInt("studentId"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setDepartment(resultSet.getString("department"));
        user.setGraduationYear(resultSet.getInt("graduationYear"));
        user.setProfession(resultSet.getString("profession"));
        user.setContactNumber(resultSet.getString("contactNumber"));
        user.setDateTime(resultSet.getObject("dateTime", LocalDateTime.class));
        user.setAddress(resultSet.getString("address"));
        return user;
    }
}
