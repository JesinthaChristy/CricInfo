package com.cricket.app.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattingScoreDTO {
    private String playerName;
    private Integer runsScored;
    private Integer ballsFaced;
    private Integer fours;
    private Integer sixes;
    private Double strikeRate;
    private String dismissalType;
    private String dismissalInfo;
}