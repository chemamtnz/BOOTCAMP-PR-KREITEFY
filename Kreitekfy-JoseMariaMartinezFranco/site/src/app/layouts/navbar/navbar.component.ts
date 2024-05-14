import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  token: string | undefined;
  decodedToken: any;
  isAuthenticated: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.token = this.authService.getToken();
    if (this.token) {
      this.decodedToken = this.authService.decodeToken(this.token);
      this.isAuthenticated = true;
    }
  }

  logout(): void {
    this.authService.logout();
    window.location.reload();
  }
  

  login(): void {
    this.router.navigate(['../login']);
  }

}
