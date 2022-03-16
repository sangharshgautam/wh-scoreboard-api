import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_GB } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FixturesComponent } from './fixtures/fixtures.component';
import {NzTableModule} from "ng-zorro-antd/table";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {httpInterceptorProviders} from "./http-interceptors";
import { ScoreboardComponent } from './scoreboard/scoreboard.component';
import {NzCardModule} from "ng-zorro-antd/card";
import { LoginComponent } from './login/login.component';
import {NzFormModule} from "ng-zorro-antd/form";
import {NzInputModule} from "ng-zorro-antd/input";
import {NzButtonModule} from "ng-zorro-antd/button";
import { ScoreboardManagerComponent } from './scoreboard-manager/scoreboard-manager.component';
import {NzPageHeaderModule} from "ng-zorro-antd/page-header";
import {NzDescriptionsModule} from "ng-zorro-antd/descriptions";
import {NzInputNumberModule} from "ng-zorro-antd/input-number";
import {NzIconModule} from "ng-zorro-antd/icon";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzToolTipModule} from "ng-zorro-antd/tooltip";
import {NzNotificationServiceModule} from "ng-zorro-antd/notification";

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    FixturesComponent,
    ScoreboardComponent,
    LoginComponent,
    ScoreboardManagerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzTableModule,
    NzDividerModule,
    NzCardModule,
    NzFormModule,
    NzInputModule,
    ReactiveFormsModule,
    NzButtonModule,
    NzPageHeaderModule,
    NzDescriptionsModule,
    NzInputNumberModule,
    NzIconModule,
    NzLayoutModule,
    NzToolTipModule,
    NzNotificationServiceModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_GB }, httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
