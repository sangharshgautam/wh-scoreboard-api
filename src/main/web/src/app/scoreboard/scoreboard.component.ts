import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FixtureService} from "../fixture.service";
import {Game} from '../model/Game';
import {BehaviorSubject, Subject} from "rxjs";
import * as Stomp from "stompjs";
import * as SockJS from "sockjs-client";
import {TeamScore} from "../model/team-score";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-scoreboard',
  templateUrl: './scoreboard.component.html',
  styleUrls: ['./scoreboard.component.css']
})
export class ScoreboardComponent implements OnInit {
  webSocketEndPoint: string = `${environment.apiEndpoint}/ws`;

  @Input()
  gameId:string;
  subject$ = new Subject<Game>();
  game: Game | undefined;
  teamScores: TeamScore[] = []
  public loading$ = new BehaviorSubject<boolean>(true);
  constructor(private route:ActivatedRoute, private fixtureService: FixtureService){
    this.gameId = this.route.snapshot.params['id'];
  }


  ngOnInit(): void {
    this.subject$.subscribe(result => {
      this.game = result;
      this.teamScores = result.teamScores
    });
    this.getData();
    this._connect();
  }
  async getData() {
    this.fixtureService.get(this.gameId).subscribe(result => this.subject$.next(result), error => console.log(error), () => this.loading$.next(false));
  }
  getTeamNames = () => {
    return this.teamScores?.map(teamScore => teamScore.team?.name).join((' vs '))
  }
  // @ts-ignore
  stompClient: Stomp.Client;
  customHeaders = {
    Authorization: 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0MDI4ODBiNDdmODkzYThmMDE3Zjg5M2FiYjNiMDAwMCIsImlhdCI6MTY0NzI3NDkxMywiZXhwIjoxNjQ4MTM4OTEzfQ.5N98dzYowVhw1XGsxR_VEBZNYR5LWSueOGBbDZTOH0Zi2bvqXryp4E_nvnVQeDAip658SekBi4UF-ird4BaZFA'
  }
  // appComponent: AppComponent;
  // constructor(appComponent: AppComponent){
  // this.appComponent = appComponent;
  // }
  _connect() {
    console.log("Initialize WebSocket Connection");
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({}, (frame) => {
      _this.stompClient.subscribe(`/topic/${this.gameId}`, (sdkEvent) => {
        _this.onMessageReceived(sdkEvent);
      });
      //_this.stompClient.reconnect_delay = 2000;
    }, this.errorCallBack);
  };

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect(() =>console.log('Disconnected'));
    }
    console.log("Disconnected");
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error: any) {
    console.log("errorCallBack -> " + error)
    setTimeout(() => {
      this._connect();
    }, 5000);
  }

  /**
   * Send message to sever via web socket
   * @param {*} message
   */
  _send(message: any) {
    console.log("calling logout api via web socket");
    this.stompClient.send("/app/hello", {}, JSON.stringify(message));
  }

  onMessageReceived(message: any) {
    console.log("Message Recieved from Server :: " + message);
    // this.appComponent.handleMessage(JSON.stringify(message.body));
    this.subject$.next(JSON.parse(message.body));
  }
}
