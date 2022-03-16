import { TestBed } from '@angular/core/testing';

import { FixtureService } from './fixture.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('FixtureService', () => {
  let service: FixtureService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(FixtureService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
