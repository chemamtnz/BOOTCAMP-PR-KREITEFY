import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  register(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/register`, user);
  }

  login(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/auth/login`, credentials);
  }

  saveToken(token: string): void {
    localStorage.setItem('auth_token', token);
  }

  getToken(): string | undefined {
    const token = localStorage.getItem('auth_token');
    return token !== null ? token : undefined; 
  }

  decodeToken(token: string): any {
    return jwt_decode(token);
  }

  getUserName(): string | undefined {
    const token = this.getToken();
    if (token) {
      const decodedToken: any = jwt_decode(token);
      const username = decodedToken.sub;
      return username;
    }
    return undefined;
  }

  isAuthenticated(): boolean {
    // Verificar si el token JWT está presente en el localStorage
    const token = localStorage.getItem('auth_token');
    return token !== null && token !== undefined;
  }

  logout(): void {
    localStorage.removeItem('auth_token');
  }
}
