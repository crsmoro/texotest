import { Component } from '@angular/core';
import '../style/app.scss';

@Component({
  selector: 'fat-app',
  templateUrl : './app.component.html'
})
export class AppComponent {
	
	showDashboard:boolean = true;
	showList:boolean = false;
	
	clickDashboard(): void {
		this.showDashboard = true;
		this.showList = false;
	}
	
	clickList(): void {
		this.showList = true;
		this.showDashboard = false;
	}
 }
