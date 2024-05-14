import { Component, OnInit } from '@angular/core';
import { Song } from '../model/song.model';
import { AuthService } from 'src/app/auth/service/auth.service';
import { SongService } from '../service/song.service';

@Component({
  selector: 'app-song-list',
  templateUrl: './song-list.component.html',
  styleUrls: ['./song-list.component.scss']
})
export class SongListComponent implements OnInit {
  songs: Song[] = [];

  page = 0;
  size = 18;
  sort = "name,asc";

  first = false;
  last = false;
  totalPages = 0;
  totalElements = 0;

  nameFilter?: string;
  styleFilter?: string;
  artistFilter?: string;
  albumFilter?: string;

  constructor(private authService: AuthService, private songService: SongService) { }

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.getSongs();
    }
  }

  navegarSiguiente(): void {
    this.page++;
    this.getSongs();
  }

  navegarAnterior(): void {
    if (this.page > 0) {
      this.page--;
      this.getSongs();
    }
  }

  limpiarCampos(): void {
    this.nameFilter = '';
    this.styleFilter = '';
    this.artistFilter = '';
    this.albumFilter = '';
    this.searchByFilters();
  }

  searchByFilters(): void {
    this.getSongs();
  }

  private buildFilters(): string | undefined {
    const filters: string[] = [];

    if (this.nameFilter) {
      filters.push("name:MATCH:" + this.nameFilter);
    }

    if (this.styleFilter) {
      filters.push("style:MATCH:" + this.styleFilter);
    }

    if (this.artistFilter) {
      filters.push("artist:MATCH:" + this.artistFilter);
    }

    if (this.albumFilter) {
      filters.push("album:MATCH:" + this.albumFilter);
    }

    if (filters.length > 0) {
      return filters.join(',');
    } else {
      return undefined;
    }
  }

  private getSongs(): void {
    const filters: string | undefined = this.buildFilters();
    this.songService.getAllSongs(this.page, this.size, this.sort, filters).subscribe({
      next: (data: any) => {
        this.songs = data.content;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
        this.first = data.first;
        this.last = data.last;
      },
      error: (err) => { this.handleError(err); }
    });
  }

  private handleError(error: any): void {
    console.error(error);
  }
}
