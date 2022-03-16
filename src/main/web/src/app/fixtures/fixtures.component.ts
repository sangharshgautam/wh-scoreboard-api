import { Component, OnInit } from '@angular/core';
import {FixtureService} from "../fixture.service";
import {Game} from "../model/Game";

interface Person {
  key: string;
  name: string;
  age: number;
  address: string;
}

@Component({
  selector: 'app-fixtures',
  templateUrl: './fixtures.component.html',
  styleUrls: ['./fixtures.component.css']
})
export class FixturesComponent implements OnInit {

  constructor(private fixtureService: FixtureService) { }
  games: Game[] = [];
  ngOnInit(): void {
    this.fixtureService.getAll().subscribe(list => this.games =  list);
  }
  getTeamNames = (game: Game) => {
    return game.teamScores?.map(teamScore => teamScore.team?.name).join((' vs '))
  }
}
