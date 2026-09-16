package com.jobradar.backend.dto;

import java.util.List;

public class AdzunaResponseDTO {

    private List<AdzunaJobDTO> results;

    public List<AdzunaJobDTO> getResults() {
        return results;
    }

    public void setResults(List<AdzunaJobDTO> results) {
        this.results = results;
    }
}