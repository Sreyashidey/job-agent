package com.jobradar.backend.service;

import com.jobradar.backend.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    public List<Job> getJobs(){
        return List.of(
            new Job(
                1L,
                "JPmorgan",
                "Software eng",
                "Bangalore",
                "1-3 y"
             ),
             new Job(
                        2L,
                        "American Express",
                        "Java Developer",
                        "Chennai",
                        "0-2 years"
                )
        );
    }
}
