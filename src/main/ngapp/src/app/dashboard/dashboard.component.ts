import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
	selector: 'dashboard',
	templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
	constructor(private httpClient: HttpClient) { }

	searchYear: string;
	yearsMultipleWinners: any[] = [];
	top3StudiosWinners: any[] = [];
	minIntervalProducers: any[] = [];
	maxIntervalProducers: any[] = [];
	movieWinnersByYear: any[] = [];

	ngOnInit(): void {
		this.httpClient.get('api/movies?projection=years-with-multiple-winners').subscribe((d: any) => this.yearsMultipleWinners = d.years);
		this.httpClient.get('api/movies?projection=studios-with-win-count').subscribe((d: any) => this.top3StudiosWinners = d.studios);
		this.httpClient.get('api/movies?projection=max-min-win-interval-for-producers').subscribe((d: any) => {
			this.minIntervalProducers = d.min;
			this.maxIntervalProducers = d.max;
		});
	}

	searchMovieYear(): void {
		this.httpClient.get('api/movies?winner=true' + (this.searchYear ? ('&year=' + this.searchYear) : '')).subscribe((d: any) => this.movieWinnersByYear = d.content);
	}
}
