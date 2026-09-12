package com.jobradar.backend.controller;

import com.jobradar.backend.dto.JobDTO;
import com.jobradar.backend.model.Job;
import com.jobradar.backend.service.JobIngestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class JobIngestionController {

    private final JobIngestionService jobIngestionService;

    public JobIngestionController(JobIngestionService jobIngestionService) {
        this.jobIngestionService = jobIngestionService;
    }

    @GetMapping("/api/jobs/ingest")
    public List<JobDTO> ingestJobs() {
        return jobIngestionService.fetchJobs();
    }

    @GetMapping("/api/jobs/ingest/save")
    public List<Job> ingestAndSaveJobs() {
    return jobIngestionService.ingestJobs();
}
}