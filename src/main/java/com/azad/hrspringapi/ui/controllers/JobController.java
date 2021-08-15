package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.io.entities.Job;
import com.azad.hrspringapi.service.JobService;
import com.azad.hrspringapi.ui.models.request.JobDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.JobRestResponse;
import com.azad.hrspringapi.ui.models.response.OperationStatusModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("jobs")
public class JobController {

    private ModelMapper modelMapper = new ModelMapper();

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(path = "/test")
    public String jobRouterTest() {
        return "Job controller is up and running";
    }

    @PostMapping
    public ResponseEntity<JobRestResponse> createJob(@RequestBody JobDetailRequestModel jobDetails) {
        Job job = modelMapper.map(jobDetails, Job.class);

        Job createdJob = jobService.createJob(job);

        JobRestResponse responseBody = modelMapper.map(createdJob, JobRestResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<JobRestResponse>> getJobs(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<Job> jobs = jobService.getJobs(page, limit);

        List<JobRestResponse> jobResponseList = new ArrayList<>();
        jobs.forEach(job -> jobResponseList.add(modelMapper.map(job, JobRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(jobResponseList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<JobRestResponse> getJob(@PathVariable Long id) {
        Job job = jobService.getJobById(id);

        JobRestResponse responseBody = modelMapper.map(job, JobRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<JobRestResponse> updateJob(@PathVariable Long id, @RequestBody JobDetailRequestModel jobDetails) {
        Job job = modelMapper.map(jobDetails, Job.class);

        Job updatedJob = jobService.updateJob(id, job);

        JobRestResponse responseBody = modelMapper.map(updatedJob, JobRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<OperationStatusModel> deleteJob(@PathVariable Long id) {
        OperationStatusModel responseBody = new OperationStatusModel();
        responseBody.setOperationName("DELETE");
        jobService.deleteJob(id);
        responseBody.setOperationResult("SUCCESS");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
