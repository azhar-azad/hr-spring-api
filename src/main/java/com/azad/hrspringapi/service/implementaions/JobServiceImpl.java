package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.JobServiceException;
import com.azad.hrspringapi.io.entities.Job;
import com.azad.hrspringapi.io.repositories.JobRepository;
import com.azad.hrspringapi.service.JobService;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {

        return jobRepository.save(job);
    }

    @Override
    public List<Job> getJobs(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("id"));
        List<Job> jobs = jobRepository.findAll(pageable).getContent();

        if (jobs.size() == 0)
            throw new JobServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());

        return jobs;
    }

    @Override
    public Job getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job == null)
            throw new JobServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        return job;
    }

    @Override
    public Job updateJob(Long id, Job updatedJobData) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job == null)
            throw new JobServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        String updatedJobTitle = updatedJobData.getJobTitle();
        if (updatedJobTitle != null && !updatedJobTitle.equals(""))
            job.setJobTitle(updatedJobTitle);
        String updatedMinSalary = Double.toString(updatedJobData.getMinSalary());
        if (updatedMinSalary != null && !updatedMinSalary.equals(""))
            job.setMinSalary(updatedJobData.getMinSalary());
        String updatedMaxSalary = Double.toString(updatedJobData.getMaxSalary());
        if (updatedMaxSalary != null && !updatedMaxSalary.equals(""))
            job.setMaxSalary(updatedJobData.getMaxSalary());

        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElse(null);

        if (job == null)
            throw new JobServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        jobRepository.delete(job);
    }
}
