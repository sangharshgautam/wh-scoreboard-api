package com.sangharsh.oms.controller;

import com.sangharsh.oms.dto.GameDTO;
import com.sangharsh.oms.dto.Greeting;
import com.sangharsh.oms.dto.TeamDTO;
import com.sangharsh.oms.model.Game;
import com.sangharsh.oms.model.Team;
import com.sangharsh.oms.service.GameService;
import com.sangharsh.oms.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GameController.ROOT)
public class GameController extends GenericController<GameService, Game, GameDTO>{

    public static final String ROOT = "/api/game";

    @Autowired
    GameController(GameService service) {
        super(service);
    }
}
