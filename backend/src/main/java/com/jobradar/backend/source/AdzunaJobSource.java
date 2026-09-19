package com.jobradar.backend.source;

import com.jobradar.backend.dto.AdzunaResponseDTO;
import com.jobradar.backend.dto.JobDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class AdzunaJobSource implements JobSource {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
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
}