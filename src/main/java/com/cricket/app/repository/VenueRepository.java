package com.cricket.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricket.app.entity.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Optional<Venue> findByVenueName(String venueName);
    
    @Query("SELECT v FROM Venue v WHERE LOWER(v.city) = LOWER(:city)")
    List<Venue> findByCity(@Param("city") String city);
}