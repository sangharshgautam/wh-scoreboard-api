import { TestBed } from '@angular/core/testing';

import { TeamscoreService } from './teamscore.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('TeamscoreService', () => {
  let service: TeamscoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(TeamscoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
