package com.almamater.user.controllers;
import java.util.List;

import com.almamater.user.dao.UserDao;
import com.almamater.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alma-mater/")
public class UserController {

    @Autowired
    private UserDao userDao;
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        boolean success = userDao.addUser(user);
        if (success) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getWith/{studentId}/{password}")
    public ResponseEntity<User> getUserByUsernameAndPassword(@PathVariable int studentId, @PathVariable String password) {
        User user = userDao.getUserByUsernameAndPassword(studentId, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllAlumni() {
        List<User> alumniList = userDao.getAllAlumni();
        return new ResponseEntity<>(alumniList, HttpStatus.OK);
    }

    @GetMapping("getUser/{studentId}")
    public ResponseEntity<User> getUserByStudentId(@PathVariable int studentId) {
        User user = userDao.getUserByStudentId(studentId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        boolean success = userDao.updateUser(user);
        if (success) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int userId) {
        boolean success = userDao.deleteUserById(userId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
