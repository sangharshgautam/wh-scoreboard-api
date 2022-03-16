import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ScoreboardComponent} from "./scoreboard/scoreboard.component";
import {FixturesComponent} from "./fixtures/fixtures.component";
import {LoginComponent} from "./login/login.component";
import {ScoreboardManagerComponent} from "./scoreboard-manager/scoreboard-manager.component";

const routes: Routes = [
  {path: 'game/:id/scoreboard/view', component: ScoreboardComponent},
  {path: 'game/:id/scoreboard/manage', component: ScoreboardManagerComponent},
  {path: 'fixtures', component: FixturesComponent},
  {path: 'login', component: LoginComponent},
  { path: '',   redirectTo: '/fixtures', pathMatch: 'full' },
  // { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
