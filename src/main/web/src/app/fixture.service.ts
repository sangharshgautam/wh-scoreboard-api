import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GenericService} from "./generic.service";
import {Game} from "./model/Game";

@Injectable({
  providedIn: 'root'
})
export class FixtureService extends GenericService<Game> {

  constructor(http: HttpClient) {
    super(http, 'game');
  }
}
