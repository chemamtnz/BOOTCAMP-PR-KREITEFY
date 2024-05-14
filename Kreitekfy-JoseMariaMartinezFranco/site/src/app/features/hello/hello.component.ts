import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../auth/service/auth.service';
import { Song } from 'src/app/entities/song/model/song.model';
import { SongService } from 'src/app/entities/song/service/song.service';

@Component({
  selector: 'app-hello',
  templateUrl: './hello.component.html',
  styleUrls: ['./hello.component.scss']
})
export class HelloComponent implements OnInit {
  message = "hola";
  newSongs: Song[] = [];
  popularSongs: Song[] = [];
  forYouSongs: Song[] = [];

  isAuthenticated: boolean = false;

  page: number = 0;
  size: number = 5;

  sortById: string = "id,desc";
  sortByReproductions: string = "reproductions,desc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  styleFilter?: string;

  constructor(private http: HttpClient, private authService: AuthService, private songService: SongService) { }

  ngOnInit(): void {
    const token = this.authService.getToken();
    this.http.get<any>('http://localhost:8080/hello', {
      headers: { Authorization: `Bearer ${token}` }
    }).subscribe({
      next: (response) => {
        this.message = response.message;
        this.isAuthenticated = true;
      },
      error: (error) => {
        console.error('Error fetching hello message', error);
      }
    });
    this.getNewSongs();
    this.getPopularSongs();
    this.getForYouSongs();

  }

  public navegarSiguiente(): void {
    this.page++;
    this.getNewSongs();
    this.getPopularSongs();
    this.getForYouSongs();

}


public navegarAnterior(): void {
    if (this.page > 0) {
        this.page--;
        this.getNewSongs();
        this.getPopularSongs();
        this.getForYouSongs();
    }
}



  private handleError(error: any): void {
    console.log(error);
  }

  private buildFilters():string|undefined {
    const filters: string[] = [];

    if(this.styleFilter) {
      filters.push("style:MATCH:" + this.styleFilter);
    }

    if (filters.length >0) {

      let globalFilters: string = "";
      for (let filter of filters) {
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0, globalFilters.length-1);
      return globalFilters;

    } else {
      return undefined;
    }
  }

  public limpiarCampos() {
    this.styleFilter = '';
    this.searchByFilters();
  }

  public searchByFilters():void {
    this.getNewSongs();
    this.getPopularSongs();
    this.getForYouSongs();
  }

  private getNewSongs(): void {

    const filters: string | undefined = this.buildFilters();
    this.songService.getAllSongs(this.page, this.size, this.sortById, filters).subscribe({
        next: (data: any) => {
            this.newSongs = data.content;
            this.totalPages = data.totalPages;
            this.totalElements = data.totalElements;
            this.first = data.first;
            this.last = data.last;
        },
        error: (err) => { this.handleError(err); }
    });
    
  }

  private getPopularSongs(): void {

    const filters: string | undefined = this.buildFilters();
    this.songService.getAllSongs(this.page, this.size, this.sortByReproductions, filters).subscribe({
        next: (data: any) => {
            this.popularSongs = data.content;
            this.totalPages = data.totalPages;
            this.totalElements = data.totalElements;
            this.first = data.first;
            this.last = data.last;
        },
        error: (err) => { this.handleError(err); }
    });
    
  }

  private getForYouSongs(): void {
    const valorationFilter = 'valoration:GREATER_THAN:2';
    this.songService.getAllSongs(this.page, this.size, this.sortByReproductions, valorationFilter).subscribe({
      next: (data: any) => {
        this.forYouSongs = data.content;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
        this.first = data.first;
        this.last = data.last;
      },
      error: (err) => { this.handleError(err); }
    });
  }
  
}
