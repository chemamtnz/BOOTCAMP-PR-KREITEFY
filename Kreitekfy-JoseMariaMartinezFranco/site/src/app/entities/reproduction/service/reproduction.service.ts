import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Reproduction } from '../model/reproduction.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/auth/service/auth.service';
import { UserService } from 'src/app/auth/user/service/user.service';

@Injectable({
    providedIn: 'root'
  })
  export class ReproductionService {

    constructor(private http: HttpClient, private authService: AuthService, private userService: UserService) { }

    public getAllReproductions(page: number, size: number, sort: string, filters?: string, headers?: HttpHeaders): Observable<any[]> {
      let urlEndpoint: string = "http://localhost:8080/reproductions?page=" + page + "&size=" + size + "&sort=" + sort;
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

    public getReproductionsByUserName(userName: String, page:number, size:number, sort:string): Observable<any[]> {
      if (userName) {
        let urlEndpoint: string = `http://localhost:8080/reproductions/userName?userName=${userName}&page=${page}&size=${size}&sort=${sort}`;
        console.error(urlEndpoint);
        const token = this.authService.getToken();
        if (token) {
          const headers = new HttpHeaders({
            Authorization: `Bearer ${token}`
          });
          return this.http.get<any[]>(urlEndpoint, { headers });
        } else {
          console.error('No se encontró el token de autenticación.');
          return throwError('No se encontró el token de autenticación.');
        }
      } else {
        console.error('No se pudo obtener el nombre de usuario.');
        return throwError('No se pudo obtener el nombre de usuario.');
      }
    }
    

    public insertReproduction(reproduction: Reproduction): Observable<Reproduction> {
      const urlEndpoint: string = "http://localhost:8080/reproductions";
      const token = this.authService.getToken();
      if(token){
        const headers = new HttpHeaders({
          Authorization: `Bearer ${token}`
        });
        return this.http.post<Reproduction>(urlEndpoint, reproduction, {headers});
      } else {
        console.error('No se encontró el token de autenticación.');
        return throwError('No se encontró el token de autenticación.');
      }
    }

  }
