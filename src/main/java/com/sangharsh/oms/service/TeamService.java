package com.sangharsh.oms.service;

import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.mapper.TeamMapper;
import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeamService extends GenericService<TeamRepository, Team, TeamDTO> {

    @Autowired
    public TeamService(
            TeamRepository storeRepository,
            TeamMapper teamMapper
    ){
        super(storeRepository, teamMapper);
    }

}
