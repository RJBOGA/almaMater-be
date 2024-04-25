package com.almamater.user.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    private int studentId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private int graduationYear;
    private String profession;
    private String contactNumber;
    private LocalDateTime dateTime;
    private String address;

    public User() {
    }

    public User(int studentId, String email, String password, String firstName, String lastName, String department,
                int graduationYear, String profession, String contactNumber, LocalDateTime dateTime, String address) {
        this.studentId = studentId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.graduationYear = graduationYear;
        this.profession = profession;
        this.contactNumber = contactNumber;
        this.dateTime = dateTime;
        this.address = address;
    }

    // Getters and Setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "studentId=" + studentId + ", email='" + email + '\'' + ", password='" + password + '\''
                + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", department='" + department
                + '\'' + ", graduationYear=" + graduationYear + ", profession='" + profession + '\''
                + ", contactNumber='" + contactNumber + '\'' + ", dateTime=" + dateTime + ", address='" + address + '\''
                + '}';
    }
}