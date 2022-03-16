package com.sangharsh.oms.controller;

import com.sangharsh.oms.dto.TeamScoreDTO;
import com.sangharsh.oms.model.TeamScore;
import com.sangharsh.oms.service.TeamScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TeamScoreController.ROOT)
public class TeamScoreController extends GenericController<TeamScoreService, TeamScore, TeamScoreDTO>{

    public static final String ROOT = "/api/teamscore";

    @Autowired
    TeamScoreController(TeamScoreService service) {
        super(service);
    }
}
