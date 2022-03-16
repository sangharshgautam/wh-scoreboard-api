package com.sangharsh.oms.service;

import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.dto.TeamScoreDTO;
import com.sangharsh.oms.exception.ResourceNotFoundException;
import com.sangharsh.oms.mapper.GameMapper;
import com.sangharsh.oms.mapper.TeamMapper;
import com.sangharsh.oms.mapper.TeamScoreMapper;
import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.model.TeamScore;
import com.sangharsh.oms.repository.TeamRepository;
import com.sangharsh.oms.repository.TeamScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeamScoreService extends GenericService<TeamScoreRepository, TeamScore, TeamScoreDTO> {

    private final GameMapper gameMapper;

    @Autowired
    public TeamScoreService(
            TeamScoreRepository repository,
            TeamScoreMapper teamScoreMapper,
            GameMapper gameMapper
    ){
        super(repository, teamScoreMapper);
        this.gameMapper = gameMapper;
    }
    @Override
    public TeamScore update(String id, TeamScoreDTO dto) {
        TeamScore updated = super.update(id, dto);
        messagingTemplate.convertAndSend(String.format("/topic/%s", updated.getGame().getId()), gameMapper.toDto(updated.getGame()));
        return updated;
    }
}
