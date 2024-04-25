package com.almamater.user.controllers;

import com.almamater.user.dao.JobDao;
import com.almamater.user.dao.impl.JobDaoImpl;
import com.almamater.user.entities.Jobs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alma-mater")
public class JobController {

    private final JobDao jobDao = new JobDaoImpl();

    @PostMapping("/createJob")
    public ResponseEntity<String> createJob(@RequestBody Jobs job) {
        String result = jobDao.createJob(job);
        if (result.startsWith("Error")) {
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllJobs")
    public ResponseEntity<List<Jobs>> getAllJobs() {
        List<Jobs> jobs = jobDao.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @DeleteMapping("/deleteJob/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable int jobId) {
        boolean success = jobDao.deleteJob(jobId);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userJobs/{userId}")
    public ResponseEntity<List<Jobs>> getJobsOfUser(@PathVariable int userId) {
        List<Jobs> jobs = jobDao.getJobsOfUser(userId);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
}

