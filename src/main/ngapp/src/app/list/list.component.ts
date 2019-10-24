import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs'
import { distinctUntilChanged, debounceTime } from 'rxjs/operators'

@Component({
	selector: 'list',
	templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {

	constructor(private httpClient: HttpClient) { }
	
	movies: any[] = [];
	pageable:any;
	pages: any[] = [];
	totalPages: number;
	firstPage: boolean;
	lastPage: boolean;
	
	private subjectYearField: Subject<string> = new Subject();
	
	 winnerFilter : string = '';
	 yearFilter : string;

	ngOnInit(): void {
		this.loadMovies();
		this.subjectYearField.pipe(
            debounceTime( 350 ),
            distinctUntilChanged()
        ).subscribe( value => {
			this.loadMovies();
		});
	}
	
	loadMovies(page?:string) : void {
		this.httpClient.get('api/movies?' + (page ? '&page=' + page : '') + (this.winnerFilter ? '&winner=' + this.winnerFilter : '') + (this.yearFilter ? '&year=' + this.yearFilter : '')).subscribe((d: any) => {
			this.movies = d.content;
			this.pageable = d.pageable;
			this.totalPages = d.totalPages;
			this.firstPage = d.first;
			this.lastPage = d.last;
			this.pages = [...Array.apply(null, {length: d.totalPages}).map(Number.call, Number)];
		});
	}
	
	filterYearKeyUp(value:string) : void {
		this.subjectYearField.next(value);
	}
}
