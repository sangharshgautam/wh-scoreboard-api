package com.sangharsh.oms.mapper;

import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.dto.TeamScoreDTO;
import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.model.TeamScore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = "spring", uses = TeamMapper.class
)
@Component

public interface TeamScoreMapper extends GenericMapper<TeamScore, TeamScoreDTO> {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "game", ignore = true)
    TeamScore fromTwoOther(TeamScoreDTO dto, @MappingTarget TeamScore entity);
}
