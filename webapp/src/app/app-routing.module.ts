import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DefaultComponent, OtherComponent, RestComponent, ClientCallbackComponent} from './app.component'

const routes: Routes = [
  {path: '', pathMatch: 'full', component: DefaultComponent},
  {path: 'other', component: OtherComponent},
  {path: 'rest', component: RestComponent},
  {path: 'clientCallback', component: ClientCallbackComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
