import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from '../model/user.model';
import { AuthService } from 'src/app/auth/service/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient, private authService: AuthService) { }

  getUserByUserName(username: string): Observable<any> {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      return this.http.get<any>(`${this.baseUrl}/users/${username}`, { headers });
    } else {
      console.log("No se encontró el token de autenticación");
      return throwError("No se encontró el token de autenticación");
    }
  }

  updateUser(user: User): Observable<User> {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`
      });

      return this.http.patch<User>(`${this.baseUrl}/users/${user.username}`, user, { headers });
    } else {
      console.log("No se encontró el token de autenticación");
      return throwError("No se encontró el token de autenticación");
    }
  }
}
