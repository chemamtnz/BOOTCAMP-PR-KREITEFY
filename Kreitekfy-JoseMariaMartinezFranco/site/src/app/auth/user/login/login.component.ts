import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  credentials: any = {};
  errorMessage: string = '';
  token: string | undefined;
  decodedToken: any;

  constructor(private authService: AuthService, private router: Router) { }

  login(): void {
    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        this.errorMessage = '';
        this.token = response.token;
        this.authService.saveToken(response.token);
        
        // Decode token if it exists
        if (this.token) {
          this.decodedToken = this.authService.decodeToken(this.token);
          console.log('Bienvenido ' + this.decodedToken.sub); // Imprimir sub del token decodificado
        }
        
        this.router.navigate(['/hello']);
      },
      error: (error) => {
        console.error('Login failed', error);
        this.errorMessage = 'Error en la autenticación. Por favor, revisa tus credenciales e intenta de nuevo.';
      }
    });
  }
}
