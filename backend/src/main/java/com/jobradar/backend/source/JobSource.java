package com.jobradar.backend.source;

import com.jobradar.backend.dto.JobDTO;

import java.util.List;

public interface JobSource {

    List<JobDTO> fetchJobs();
}