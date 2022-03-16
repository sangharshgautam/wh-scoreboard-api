package com.sangharsh.oms.mapper;

import com.sangharsh.oms.dto.GameDTO;
import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.model.Game;
import com.sangharsh.oms.model.Team;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = "spring", uses = TeamScoreMapper.class
)
@Component
public interface GameMapper extends GenericMapper<Game, GameDTO> {

}
