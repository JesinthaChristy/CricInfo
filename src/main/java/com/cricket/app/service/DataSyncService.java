package com.cricket.app.service;

import com.cricket.app.entity.*;
import com.cricket.app.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class DataSyncService {

    private final CricketApiService cricketApiService;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final InningsRepository inningsRepository;
    private final BattingScoreRepository battingScoreRepository;
    private final BowlingScoreRepository bowlingScoreRepository;

    public DataSyncService(
            CricketApiService cricketApiService,
            MatchRepository matchRepository,
            TeamRepository teamRepository,
            PlayerRepository playerRepository,
            InningsRepository inningsRepository,
            BattingScoreRepository battingScoreRepository,
            BowlingScoreRepository bowlingScoreRepository) {
        this.cricketApiService = cricketApiService;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.inningsRepository = inningsRepository;
        this.battingScoreRepository = battingScoreRepository;
        this.bowlingScoreRepository = bowlingScoreRepository;
    }

    @Transactional
    public void syncCurrentMatches() {
        JsonNode matches = cricketApiService.getCurrentMatches();

        if (matches.has("data")) {
            for (JsonNode matchData : matches.get("data")) {
                syncMatchData(matchData);
            }
        }
    }

    @Transactional
    public void syncMatchScorecard(String apiMatchId) {
        JsonNode scorecardData = cricketApiService.getMatchScorecard(apiMatchId);

        if (scorecardData.has("data")) {
            processScorecard(scorecardData.get("data"));
        }
    }

    private void syncMatchData(JsonNode matchData) {
        Team team1 = getOrCreateTeam(matchData.get("teams").get(0).asText());
        Team team2 = getOrCreateTeam(matchData.get("teams").get(1).asText());

        Match match = Match.builder()
            .matchNumber(matchData.get("id").asText())
            .seriesName(matchData.get("name").asText())
            .matchType(matchData.get("matchType").asText())
            .team1(team1)
            .team2(team2)
            .matchStatus(matchData.get("status").asText())
            .matchDate(LocalDate.now())
            .build();

        matchRepository.save(match);
    }

    private void processScorecard(JsonNode scorecardData) {
        if (scorecardData.has("innings")) {
            for (JsonNode inningsData : scorecardData.get("innings")) {
                processInnings(inningsData);
            }
        }
    }

    private void processInnings(JsonNode inningsData) {

    }

    private Team getOrCreateTeam(String teamName) {
        return teamRepository.findByTeamName(teamName)
            .orElseGet(() -> {
                Team team = Team.builder()
                    .teamName(teamName)
                    .teamCode(teamName.substring(0, 3).toUpperCase())
                    .country(teamName)
                    .logoUrl(teamName)
                    .build();
                return teamRepository.save(team);
            });
    }
}