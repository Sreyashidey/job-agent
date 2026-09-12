package com.jobradar.backend.controller;

import com.jobradar.backend.dto.JobDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MockJobController {

    @GetMapping("/mock/jobs")
    public List<JobDTO> getExternalJobs() {

        JobDTO job1 = new JobDTO();
        job1.setCompany("JPMorgan");
        job1.setTitle("Software Engineer");
        job1.setLocation("Bangalore");
        job1.setExperience("1-3 years");
        job1.setSource("JPMorgan");
job1.setExternalJobId("JP12345");
job1.setUrl("https://example.com/jobs/JP12345");

        JobDTO job2 = new JobDTO();
        job2.setCompany("American Express");
        job2.setTitle("Java Developer");
        job2.setLocation("Chennai");
        job2.setExperience("0-2 years");
        job2.setSource("American Express");
job2.setExternalJobId("AMEX67890");
job2.setUrl("https://example.com/jobs/AMEX67890");

        return List.of(job1, job2);
    }
}