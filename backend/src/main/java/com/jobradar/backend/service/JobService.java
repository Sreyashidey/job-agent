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

public List<Job> getJobs(String company){
    if(company!=null && !company.isBlank()){
        return jobRepository.findByCompanyIgnoreCase(company);
    }
    return jobRepository.findAll();
}
public Job createJob(Job job){
    return jobRepository.save(job);
}
    }

