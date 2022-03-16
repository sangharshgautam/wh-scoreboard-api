import {BaseModel} from "./base";

export interface Embedded<T extends BaseModel>{
  game: T[]
}
