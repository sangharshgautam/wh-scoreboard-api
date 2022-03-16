import {ViewDTO} from "./ViewDTO";
import {TeamScore} from "./team-score";

export interface Game extends ViewDTO{
  teamScores: TeamScore[];
  status: 'SCHEDULED' | 'IN_PROGRESS' | 'FINISHED';
}
