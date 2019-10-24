import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './not-found.component';
import { AppComponent } from './app.component'

const appRoutes: Routes = [
	{ path: '', component: AppComponent },
	{ path: '**', component: PageNotFoundComponent }
];


@NgModule({
	imports: [
		RouterModule.forRoot(appRoutes)
	],
	exports: [
		RouterModule
	]
})
export class AppRoutingModule { }