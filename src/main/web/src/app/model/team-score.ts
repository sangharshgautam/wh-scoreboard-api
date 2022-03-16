import {BaseModel} from "./base";
import {Team} from "./team";

export interface TeamScore extends BaseModel{
  team?: Team;
  score: number;
}
