package com.almamater.user.dao;

import com.almamater.user.entities.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User user);

    User getUserByUsernameAndPassword(int studentId, String password);
    List<User> getAllAlumni();
    User getUserByStudentId(int studentId);
    boolean updateUser(User user);
    boolean deleteUserById(int userId);
}
