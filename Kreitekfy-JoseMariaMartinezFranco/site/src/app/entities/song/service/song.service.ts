import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Song } from '../model/song.model';
import { AuthService } from 'src/app/auth/service/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SongService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  public getAllSongs(page: number, size: number, sort: string, filters?: string, headers?: HttpHeaders): Observable<any[]> {
    let urlEndpoint: string = "http://localhost:8080/songs?page=" + page + "&size=" + size + "&sort=" + sort;
    const token = this.authService.getToken();

    if(token){
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      if(filters){
        urlEndpoint = urlEndpoint + "&filter=" + filters;
      }

      return this.http.get<any>(urlEndpoint, {headers});
    } else {
      console.error('No se encontró el token de autenticación.');

      return throwError('No se encontró el token de autenticación.');
    }

  }

  public getSongById(songId: number): Observable<Song> {
    const urlEndpoint: string = `http://localhost:8080/songs/${songId}`;
    console.log(songId);

    const token = this.authService.getToken();

    if(token){
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      return this.http.get<Song>(urlEndpoint, {headers});
    } else {
      console.error('No se encontró el token de autenticación.');

      return throwError('No se encontró el token de autenticación.');
    }
  }

  public updateSong(song: Song): Observable<Song> {
    const urlEndpoint: string = `http://localhost:8080/songs/${song.id}`;
    const token = this.authService.getToken();
    if(token){
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });
      return this.http.put<Song>(urlEndpoint, song, {headers});
    } else {
      console.error('No se encontró el token de autenticación.');
      return throwError('No se encontró el token de autenticación.');
    }
  }
}
