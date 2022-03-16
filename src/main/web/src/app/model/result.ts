import {BaseModel} from "./base";
import {Embedded} from "./embedded";

export interface Result<T extends BaseModel>{
  _embedded: Embedded<T>;
}
