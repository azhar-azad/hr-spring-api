package com.azad.hrspringapi.service;

import com.azad.hrspringapi.io.entities.Job;

import java.util.List;

public interface JobService {

    Job createJob(Job job);

    Job getJobById(Long id);

    List<Job> getJobs(int page, int limit);

    Job updateJob(Long id, Job job);

    void deleteJob(Long id);
}
