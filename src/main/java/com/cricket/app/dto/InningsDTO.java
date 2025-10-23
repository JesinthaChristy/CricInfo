package com.cricket.app.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InningsDTO {
    private Long inningsId;
    private Integer inningsNumber;
    private String battingTeam;
    private String bowlingTeam;
    private Integer totalRuns;
    private Integer totalWickets;
    private Double totalOvers;
    private Integer extras;
    private List<BattingScoreDTO> battingScores;
    private List<BowlingScoreDTO> bowlingScores;
}