import {HttpClient} from "@angular/common/http";
import {ViewDTO} from "./model/ViewDTO";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";


export abstract class GenericService<T extends ViewDTO> {

  private api_root = `${environment.apiEndpoint}/api`;
  protected constructor(protected http: HttpClient, protected path: string) { }

  getAll(): Observable<T[]> {
    return this.http.get<T[]>([this.api_root, this.path].join('/'));
  }

  get(id: string): Observable<T> {
    return this.http.get<T>([this.api_root, this.path, id].join('/'));
  }
  update(id: string, payload: T): Observable<T> {
    return this.http.put<T>([this.api_root, this.path, id].join('/'), payload);
  }
}
