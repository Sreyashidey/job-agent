package com.jobradar.backend.service;

import com.jobradar.backend.dto.JobDTO;
import com.jobradar.backend.model.Job;
import com.jobradar.backend.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;



@Service
public class JobIngestionService {

    private final JobRepository jobRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public JobIngestionService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobDTO> fetchJobs() {

        String url = "http://localhost:8080/mock/jobs";

        JobDTO[] jobs = restTemplate.getForObject(url, JobDTO[].class);

        return Arrays.asList(jobs);
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