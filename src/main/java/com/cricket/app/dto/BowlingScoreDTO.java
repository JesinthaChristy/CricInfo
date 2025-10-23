package com.cricket.app.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BowlingScoreDTO {
    private String playerName;
    private Double oversBowled;
    private Integer maidens;
    private Integer runsConceded;
    private Integer wicketsTaken;
    private Double economyRate;
}