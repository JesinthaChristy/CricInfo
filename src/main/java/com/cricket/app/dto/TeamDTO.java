package com.cricket.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDTO {
 private Long teamId;
 private String teamName;
 private String teamCode;
 private String country;
 private String logoUrl;
}