package com.sangharsh.oms.mapper;

import com.sangharsh.oms.dto.TeamScoreDTO;
import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.model.TeamScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class TeamScoreMapperTest {
    private TeamScoreMapper mapper
            = Mappers.getMapper(TeamScoreMapper.class);
    @Test
    public void fromTwoOtherTest() {
        TeamScoreDTO dto = new TeamScoreDTO();
        dto.setScore(2);
        TeamScore entity = new TeamScore();
        Team team = Team.builder().name("Team A").build();
        entity.setTeam(team);
        TeamScore destination = mapper.fromTwoOther(dto, entity);

        Assertions.assertEquals(destination.getScore(), 2);
        Assertions.assertEquals(destination.getTeam(), team);
    }
}
