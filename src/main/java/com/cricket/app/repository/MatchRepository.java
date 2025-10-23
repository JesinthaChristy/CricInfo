package com.cricket.app.repository;

import com.cricket.app.entity.Match;
import com.cricket.app.entity.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByMatchStatus(String status);
    List<Match> findByMatchDateBetween(LocalDate startDate, LocalDate endDate);
    List<Match> findByTeam1OrTeam2(Team team1, Team team2);
    
 // Fetch match with venue using JOIN FETCH to avoid N+1 problem
    @Query("SELECT m FROM Match m LEFT JOIN FETCH m.venue WHERE m.id = :matchId")
    Optional<Match> findByIdWithVenue(@Param("matchId") Long matchId);
    
    @Query("SELECT DISTINCT m FROM Match m LEFT JOIN FETCH m.venue LEFT JOIN FETCH m.team1 LEFT JOIN FETCH m.team2")
    List<Match> findAllWithDetails();
    
}

