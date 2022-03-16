import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "./model/login-response";
import {Router} from "@angular/router";
import {environment} from "../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string = 'xyz'
  public redirectUrl = null;
  constructor(private httpClient: HttpClient, private router: Router) { }

  login(loginForm: any) {
    this.httpClient.post<LoginResponse>(`${environment.apiEndpoint}/api/auth/signin`, loginForm)
      .subscribe(response => {
        this.token = response.accessToken;
        if(this.redirectUrl){
          this.router.navigate([this.redirectUrl]);
          this.redirectUrl = null;
        } else{
          this.router.navigate(['/']);
        }
      });
  }
  getAuthorizationToken() {
    return `Bearer ${this.token}`
  }
}
