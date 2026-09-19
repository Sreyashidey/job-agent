package com.jobradar.backend.service;

import com.jobradar.backend.dto.JobDTO;
import com.jobradar.backend.model.Job;
import com.jobradar.backend.repository.JobRepository;
import com.jobradar.backend.source.JobSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobIngestionService {

    private final JobRepository jobRepository;
    private final JobSource jobSource;

    public JobIngestionService(
            JobRepository jobRepository,
            JobSource jobSource) {

        this.jobRepository = jobRepository;
        this.jobSource = jobSource;
    }

    public List<JobDTO> fetchJobs() {
        return jobSource.fetchJobs();
    }

    public List<Job> ingestJobs() {

        List<JobDTO> jobDTOs = fetchJobs();

        List<Job> newJobs = jobDTOs.stream()
                .filter(dto -> !jobRepository.existsBySourceAndExternalJobId(
                        dto.getSource(),
                        dto.getExternalJobId()
                ))
                .map(dto -> new Job(
                        null,
                        dto.getCompany(),
                        dto.getTitle(),
                        dto.getLocation(),
                        dto.getExperience(),
                        dto.getSource(),
                        dto.getExternalJobId(),
                        dto.getUrl()
                ))
                .toList();

        return jobRepository.saveAll(newJobs);
    }

    @Scheduled(fixedRate = 60000)
    public void scheduledIngestion() {
        ingestJobs();
        System.out.println("Scheduled job ingestion completed.");
    }
}