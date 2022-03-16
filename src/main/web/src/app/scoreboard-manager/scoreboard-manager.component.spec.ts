import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreboardManagerComponent } from './scoreboard-manager.component';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {AppModule} from "../app.module";

describe('ScoreboardManagerComponent', () => {
  let component: ScoreboardManagerComponent;
  let fixture: ComponentFixture<ScoreboardManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, HttpClientTestingModule, AppModule],
      declarations: [ ScoreboardManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreboardManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
