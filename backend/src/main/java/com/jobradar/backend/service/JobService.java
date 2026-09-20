package com.jobradar.backend.service;
import com.jobradar.backend.specification.JobSpecification;
import org.springframework.data.jpa.domain.Specification;
import com.jobradar.backend.model.Job;
import com.jobradar.backend.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Page<Job> getJobs(
        String company,
        String location,
        String title,
        String experience,
        Pageable pageable) {

    Specification<Job> specification = Specification
            .where(JobSpecification.hasCompany(company))
            .and(JobSpecification.hasLocation(location))
            .and(JobSpecification.hasTitle(title))
            .and(JobSpecification.hasExperience(experience));

    return jobRepository.findAll(specification, pageable);
}

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
}