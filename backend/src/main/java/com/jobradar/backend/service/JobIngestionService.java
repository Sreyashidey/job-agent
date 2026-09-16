package com.jobradar.backend.service;

import com.jobradar.backend.dto.AdzunaResponseDTO;
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

    String url = "https://api.adzuna.com/v1/api/jobs/in/search/1"
            + "?app_id={appId}"
            + "&app_key={appKey}"
            + "&what=software engineer"
            + "&where=Chennai"
            + "&results_per_page=10";

    AdzunaResponseDTO response = restTemplate.getForObject(
            url,
            AdzunaResponseDTO.class,
            System.getenv("ADZUNA_APP_ID"),
            System.getenv("ADZUNA_APP_KEY")
    );

    return response.getResults().stream()
            .map(job -> {
                JobDTO dto = new JobDTO();

                dto.setCompany(job.getCompany().getDisplay_name());
                dto.setTitle(job.getTitle());
                dto.setLocation(job.getLocation().getDisplay_name());
                dto.setSource("Adzuna");
                dto.setExternalJobId(job.getId());
                dto.setUrl(job.getRedirect_url());

                return dto;
            })
            .toList();
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