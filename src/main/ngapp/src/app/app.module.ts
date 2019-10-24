import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './not-found.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module'
import { DashboardComponent } from './dashboard/dashboard.component'
import { ListComponent } from './list/list.component'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'
import { FontAwesomeIconesModule } from './app.fa-icons.module'
import { FormsModule } from '@angular/forms';

@NgModule( {
    imports: [
        BrowserModule,
		FormsModule,
        HttpClientModule,
		AppRoutingModule,
		FontAwesomeIconesModule,
		FontAwesomeModule,
    ],
    declarations: [
        AppComponent,
        PageNotFoundComponent,
		DashboardComponent,
		ListComponent
    ],
    bootstrap: [AppComponent]
} )
export class AppModule { }
