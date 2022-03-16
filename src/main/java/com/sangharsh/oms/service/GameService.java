package com.sangharsh.oms.service;

import com.sangharsh.oms.dto.GameDTO;
import com.sangharsh.oms.mapper.GameMapper;
import com.sangharsh.oms.model.Game;
import com.sangharsh.oms.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService extends GenericService<GameRepository, Game, GameDTO> {

    @Autowired
    public GameService(
            GameRepository repository,
            GameMapper mapper
    ){
        super(repository, mapper);
    }

}
