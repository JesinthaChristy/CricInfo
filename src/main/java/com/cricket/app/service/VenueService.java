package com.cricket.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cricket.app.dto.MatchDTO;
import com.cricket.app.entity.Match;
import com.cricket.app.entity.Venue;
import com.cricket.app.repository.VenueRepository;

public class VenueService {

	@Autowired
    private VenueRepository venueRepository;
	
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venue not found"));
    }
    
    public Venue getVenueByName(String venueName) {
        return venueRepository.findByVenueName(venueName)
            .orElseThrow(() -> new RuntimeException("Venue not found"));
    }
    
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }
	
}
