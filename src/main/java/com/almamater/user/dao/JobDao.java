package com.almamater.user.dao;

import com.almamater.user.entities.Jobs;

import java.util.List;

public interface JobDao {

    String createJob(Jobs postjob);

    List<Jobs> getAllJobs();

    boolean deleteJob(int jobId);

    List<Jobs> getJobsOfUser(int userId);

}
