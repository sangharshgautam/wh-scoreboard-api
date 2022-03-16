package com.sangharsh.oms.controller;

import com.sangharsh.oms.dto.Greeting;
import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TeamController.ROOT)
public class TeamController extends GenericController<TeamService, Team, TeamDTO>{

    public static final String ROOT = "/api/team";


    @Autowired
    TeamController(TeamService teamService) {
        super(teamService);
    }
}
