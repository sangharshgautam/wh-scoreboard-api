package com.sangharsh.oms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TeamScoreDTO extends ViewDTO {
    private TeamDTO team;
    private int score;
}
