package com.jobradar.backend.controller;

import com.jobradar.backend.model.Job;
import com.jobradar.backend.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService=jobService;
    }

@GetMapping
public Page<Job> getJobs(
        @RequestParam(required = false) String company,
        @RequestParam(required = false) String location,
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String experience,
        Pageable pageable) {

    return jobService.getJobs(
            company,
            location,
            title,
            experience,
            pageable
    );
}
    
    @PostMapping
    public Job createJob(@RequestBody Job job){
        return jobService.createJob(job);
    }
    
    
    
}
