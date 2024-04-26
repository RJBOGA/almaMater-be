package com.almamater.user.dao.impl;

import com.almamater.user.dao.JobDao;
import com.almamater.user.entities.Jobs;
import com.almamater.user.utils.ConnectionException;
import com.almamater.user.utils.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {

    private static final String jdbcusername = "root";
    private static final String jdbcpassword = "";

    @Override
    public String createJob(Jobs postjob) {
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword)) {
            if (connection != null) {
                System.out.println("Connected to the database!");

                String query = "INSERT INTO jobs (jobId, profile, exp, description, techs, studentId, postedBy, datePosted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, postjob.getJobId());
                preparedStatement.setString(2, postjob.getProfile());
                preparedStatement.setInt(3, postjob.getExp());
                preparedStatement.setString(4, postjob.getDesc());
                preparedStatement.setString(5, postjob.getTechs());
                preparedStatement.setInt(6, postjob.getStudentId());
                preparedStatement.setString(7, postjob.getContact());
                preparedStatement.setObject(8, LocalDate.now());

                int rows = preparedStatement.executeUpdate();
                return String.valueOf(rows);
            } else {
                System.out.println("Failed to make connection!");
                return "Failed to connect to database";
            }
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
            return "Error occurred while posting job: " + e.getMessage();
        }
    }

    @Override
    public List<Jobs> getAllJobs() {
        String selectaAll = "SELECT * FROM jobs";
        List<Jobs> jobsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectaAll)) {

            while (resultSet.next()) {
                Jobs job = mapResultSetToJob(resultSet);
                jobsList.add(job);
            }
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return jobsList;
    }

    private Jobs mapResultSetToJob(ResultSet resultSet) throws SQLException {
        Jobs job = new Jobs();
        job.setJobId(resultSet.getInt("jobId"));
        job.setProfile(resultSet.getString("profile"));
        job.setDesc(resultSet.getString("description"));
        job.setExp(resultSet.getInt("exp"));
        job.setStudentId(resultSet.getInt("studentId"));
        job.setTechs(resultSet.getString("techs"));
        job.setContact(resultSet.getString("postedBy"));
        job.setDatePosted(convertStringToDate(resultSet.getString("datePosted")));
        return job;
    }

    private static LocalDate convertStringToDate(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString, dateFormatter);
    }

    @Override
    public boolean deleteJob(int jobId) {
        String delete = "DELETE FROM jobs WHERE jobId = ?";
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword);
             PreparedStatement statement = connection.prepareStatement(delete)) {
            statement.setInt(1, jobId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Jobs> getJobsOfUser(int userId) {
        String getJobs = "SELECT * FROM jobs WHERE studentId = ?";
        List<Jobs> jobsList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection(jdbcusername, jdbcpassword);
             PreparedStatement statement = connection.prepareStatement(getJobs)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Jobs job = mapResultSetToJob(resultSet);
                jobsList.add(job);
            }
        } catch (ConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return jobsList;
    }
}
