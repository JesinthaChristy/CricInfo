package com.cricket.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cricket.app.dto.VenueDTO.VenueDTOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private Long matchId;
    private String matchNumber;
    private String seriesName;
    private String matchType;
    private TeamDTO team1;
    private TeamDTO team2;
    private VenueDTO venue;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String matchStatus;
    private String tossWinner;
    private String tossDecision;
    private String winner;
    private String resultDescription;
    private String manOfMatch;
}