package com.sangharsh.oms.dto;

import com.sangharsh.oms.model.GameStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GameDTO extends ViewDTO {
    private List<TeamScoreDTO> teamScores;
    private GameStatus status;
}
