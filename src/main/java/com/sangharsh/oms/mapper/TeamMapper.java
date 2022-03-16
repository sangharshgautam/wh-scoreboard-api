package com.sangharsh.oms.mapper;

import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.model.Team;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(
        componentModel = "spring"
)
@Component
public interface TeamMapper extends GenericMapper<Team, TeamDTO> {

}
