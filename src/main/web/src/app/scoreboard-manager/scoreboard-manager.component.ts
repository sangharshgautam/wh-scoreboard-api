import { Component, OnInit } from '@angular/core';
import {Subject} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {FixtureService} from "../fixture.service";
import {Game} from "../model/Game";
import {TeamscoreService} from "../teamscore.service";
import {ScoreboardComponent} from "../scoreboard/scoreboard.component";

@Component({
  selector: 'app-scoreboard-manager',
  templateUrl: './scoreboard-manager.component.html',
  styleUrls: ['./scoreboard-manager.component.css']
})
export class ScoreboardManagerComponent extends ScoreboardComponent{

  constructor(route:ActivatedRoute, fixtureService: FixtureService, private teamscoreService: TeamscoreService){
    super(route, fixtureService)

  }

  decreaseScore(teamScoreId: string, score: number) {
    this.teamscoreService.update(teamScoreId, {id: teamScoreId, score: score-1}).subscribe();
  }
  increaseScore(teamScoreId: string, score: number) {
    this.teamscoreService.update(teamScoreId, {id: teamScoreId, score: score+1}).subscribe();
  }
}
