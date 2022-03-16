import { Injectable } from '@angular/core';
import {GenericService} from "./generic.service";
import {TeamScore} from "./model/team-score";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TeamscoreService extends GenericService<TeamScore>{

  constructor(http: HttpClient) {
    super(http, 'teamscore');
  }
}
