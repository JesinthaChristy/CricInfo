package com.cricket.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    
    private String matchNumber;
    private String seriesName;
    private String matchType;
    
    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;
    
    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;
    
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    
    private LocalDate matchDate;
    private LocalTime matchTime;
    
    @ManyToOne
    @JoinColumn(name = "toss_winner_team_id")
    private Team tossWinner;
    
    private String tossDecision;
    private String matchStatus;
    
    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    private Team winner;
    
    private String resultDescription;
    
    @ManyToOne
    @JoinColumn(name = "man_of_match_player_id")
    private Player manOfMatch;
    
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Innings> innings;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}