package com.cricket.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cricket.app.dto.BattingScoreDTO;
import com.cricket.app.dto.BowlingScoreDTO;
import com.cricket.app.dto.InningsDTO;
import com.cricket.app.dto.MatchDTO;
import com.cricket.app.dto.ScorecardDTO;
import com.cricket.app.dto.TeamDTO;
import com.cricket.app.dto.VenueDTO;
import com.cricket.app.entity.BattingScore;
import com.cricket.app.entity.BowlingScore;
import com.cricket.app.entity.Innings;
import com.cricket.app.entity.Match;
import com.cricket.app.entity.Team;
import com.cricket.app.entity.Venue;
import com.cricket.app.repository.BattingScoreRepository;
import com.cricket.app.repository.BowlingScoreRepository;
import com.cricket.app.repository.InningsRepository;
import com.cricket.app.repository.MatchRepository;
import com.cricket.app.repository.TeamRepository;

@Service
public class MatchService {
    
    private final MatchRepository matchRepository;
    private final InningsRepository inningsRepository;
    private final BattingScoreRepository battingScoreRepository;
    private final BowlingScoreRepository bowlingScoreRepository;
    private final TeamRepository teamRepository;
    
    public MatchService(MatchRepository matchRepository,
                       InningsRepository inningsRepository,
                       BattingScoreRepository battingScoreRepository,
                       BowlingScoreRepository bowlingScoreRepository,
                       TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.inningsRepository = inningsRepository;
        this.battingScoreRepository = battingScoreRepository;
        this.bowlingScoreRepository = bowlingScoreRepository;
        this.teamRepository = teamRepository;
    }
    
    // MODIFIED: Use findAllWithDetails() instead of findAll()
    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAllWithDetails()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public List<MatchDTO> getLiveMatches() {
        return matchRepository.findByMatchStatus("LIVE")
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    
    public MatchDTO getMatchById(Long matchId) {
        Match match = matchRepository.findByIdWithVenue(matchId)
            .orElseThrow(() -> new RuntimeException("Match not found"));
        return convertToDTO(match);
    }
    
    @Transactional(readOnly = true)
    public ScorecardDTO getMatchScorecard(Long matchId) {
        Match match = matchRepository.findById(matchId)
            .orElseThrow(() -> new RuntimeException("Match not found"));
        
        List<InningsDTO> inningsDTOs = inningsRepository.findByMatchMatchId(matchId)
            .stream()
            .map(this::convertInningsToDTO)
            .collect(Collectors.toList());
        
        return ScorecardDTO.builder()
            .matchId(matchId)
            .matchDetails(convertToDTO(match))
            .innings(inningsDTOs)
            .build();
    }
    
    @Transactional
    public MatchDTO createMatch(MatchDTO matchDTO) {
        Team team1 = teamRepository.findByTeamName(matchDTO.getTeam1().getTeamName())
            .orElseGet(() -> teamRepository.save(Team.builder()
                .teamName(matchDTO.getTeam1().getTeamName())
                .teamCode(matchDTO.getTeam1().getTeamCode())
                .build()));
        
        Team team2 = teamRepository.findByTeamName(matchDTO.getTeam2().getTeamName())
            .orElseGet(() -> teamRepository.save(Team.builder()
                .teamName(matchDTO.getTeam2().getTeamName())
                .teamCode(matchDTO.getTeam2().getTeamCode())
                .build()));
        
        Match match = Match.builder()
            .seriesName(matchDTO.getSeriesName())
            .matchType(matchDTO.getMatchType())
            .matchDate(matchDTO.getMatchDate())
            .matchTime(matchDTO.getMatchTime())
            .matchStatus(matchDTO.getMatchStatus())
            .team1(team1)
            .team2(team2)
            .build();
        
        Match savedMatch = matchRepository.save(match);
        return convertToDTO(savedMatch);
    }
    
    // MODIFIED: Added venue mapping
    private MatchDTO convertToDTO(Match match) {
        return MatchDTO.builder()
            .matchId(match.getMatchId())
            .matchNumber(match.getMatchNumber())
            .seriesName(match.getSeriesName())
            .matchType(match.getMatchType())
            .team1(convertTeamToDTO(match.getTeam1())) 
            .team2(convertTeamToDTO(match.getTeam2()))
            .venue(convertVenueToDTO(match.getVenue()))
            .matchDate(match.getMatchDate())
            .matchTime(match.getMatchTime())
            .matchStatus(match.getMatchStatus())
            .tossWinner(match.getTossWinner() != null ? 
                match.getTossWinner().getTeamName() : null)
            .tossDecision(match.getTossDecision())
            .winner(match.getWinner() != null ? 
                match.getWinner().getTeamName() : null)
            .resultDescription(match.getResultDescription())
            .manOfMatch(match.getManOfMatch() != null ? 
                match.getManOfMatch().getPlayerName() : null)
            .build();
    }
    
    private InningsDTO convertInningsToDTO(Innings innings) {
        List<BattingScoreDTO> battingScores = battingScoreRepository
            .findByInningsInningsId(innings.getInningsId())
            .stream()
            .map(this::convertBattingScoreToDTO)
            .collect(Collectors.toList());
        
        List<BowlingScoreDTO> bowlingScores = bowlingScoreRepository
            .findByInningsInningsId(innings.getInningsId())
            .stream()
            .map(this::convertBowlingScoreToDTO)
            .collect(Collectors.toList());
        
        return InningsDTO.builder()
            .inningsId(innings.getInningsId())
            .inningsNumber(innings.getInningsNumber())
            .battingTeam(innings.getBattingTeam().getTeamName())
            .bowlingTeam(innings.getBowlingTeam().getTeamName())
            .totalRuns(innings.getTotalRuns())
            .totalWickets(innings.getTotalWickets())
            .totalOvers(innings.getTotalOvers())
            .extras(innings.getExtras())
            .battingScores(battingScores)
            .bowlingScores(bowlingScores)
            .build();
    }
    
    private BattingScoreDTO convertBattingScoreToDTO(BattingScore score) {
        String dismissalInfo = "";
        if (score.getDismissalType() != null) {
            dismissalInfo = score.getDismissalType();
            if (score.getBowler() != null) {
                dismissalInfo += " b " + score.getBowler().getPlayerName();
            }
            if (score.getFielder() != null) {
                dismissalInfo += " c " + score.getFielder().getPlayerName();
            }
        }
        
        return BattingScoreDTO.builder()
            .playerName(score.getPlayer().getPlayerName())
            .runsScored(score.getRunsScored())
            .ballsFaced(score.getBallsFaced())
            .fours(score.getFours())
            .sixes(score.getSixes())
            .strikeRate(score.getStrikeRate())
            .dismissalType(score.getDismissalType())
            .dismissalInfo(dismissalInfo)
            .build();
    }
    
    private BowlingScoreDTO convertBowlingScoreToDTO(BowlingScore score) {
        return BowlingScoreDTO.builder()
            .playerName(score.getPlayer().getPlayerName())
            .oversBowled(score.getOversBowled())
            .maidens(score.getMaidens())
            .runsConceded(score.getRunsConceded())
            .wicketsTaken(score.getWicketsTaken())
            .economyRate(score.getEconomyRate())
            .build();
    }
    
    // MODIFIED: Added imageUrl field
    private VenueDTO convertVenueToDTO(Venue venue) {
        if (venue == null) {
            return null;
        }
        
        return VenueDTO.builder()
            .venueId(venue.getVenueId())
            .venueName(venue.getVenueName())
            .city(venue.getCity())
            .country(venue.getCountry())
            .capacity(venue.getCapacity())// ADDED: imageUrl field
            .build();
    }
    
    private TeamDTO convertTeamToDTO(Team team) {
        if (team == null) {
            return null;
        }
        
        return TeamDTO.builder()
            .teamId(team.getTeamId())
            .teamName(team.getTeamName())
            .teamCode(team.getTeamCode())
            .country(team.getCountry())
            .logoUrl(team.getLogoUrl())
            .build();
    }
    
}