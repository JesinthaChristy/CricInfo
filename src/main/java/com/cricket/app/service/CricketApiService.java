package com.cricket.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CricketApiService {
    
    @Value("${cricket.api.base-url}")
    private String apiBaseUrl;
    
    @Value("${cricket.api.key}")
    private String apiKey;
    
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public JsonNode getCurrentMatches() {
        String url = apiBaseUrl + "/currentMatches?apikey=" + apiKey;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching current matches", e);
        }
    }
    
    public JsonNode getMatchInfo(String matchId) {
        String url = apiBaseUrl + "/match_info?apikey=" + apiKey + "&id=" + matchId;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching match info", e);
        }
    }
    
    public JsonNode getMatchScorecard(String matchId) {
        String url = apiBaseUrl + "/match_scorecard?apikey=" + apiKey + "&id=" + matchId;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readTree(response);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching scorecard", e);
        }
    }
}