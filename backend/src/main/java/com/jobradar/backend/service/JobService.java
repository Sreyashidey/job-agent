package com.jobradar.backend.service;

import com.jobradar.backend.model.Job;
import com.jobradar.backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;
     public JobService(JobRepository jobRepository){
        this.jobRepository=jobRepository;
}

public List<Job> getJobs(
        String company,
        String location,
        String title,
        String experience) {

    List<Job> jobs = jobRepository.findAll();

    return jobs.stream()
            .filter(job -> company == null ||
                    job.getCompany().equalsIgnoreCase(company))
            .filter(job -> location == null ||
                    job.getLocation().equalsIgnoreCase(location))
            .filter(job -> title == null ||
                    job.getTitle().toLowerCase().contains(title.toLowerCase()))
            .filter(job -> experience == null ||
                    job.getExperience().equalsIgnoreCase(experience))
            .toList();
}
public Job createJob(Job job){
    return jobRepository.save(job);
}
    }

