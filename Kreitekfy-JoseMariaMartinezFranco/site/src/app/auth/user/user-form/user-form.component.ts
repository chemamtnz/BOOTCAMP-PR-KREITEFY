import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { ReproductionService } from 'src/app/entities/reproduction/service/reproduction.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
  token: string | undefined;
  decodedToken: any;
  isAuthenticated: boolean = false;
  userData: any; 
  reproductions: any[] = [];
  userName!: string;

  page: number = 0;
  size: number = 5;
  sort: string = "id,asc"

  first = false;
  last = false;
  totalPages = 0;
  totalElements = 0;


  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router,
    private reproductionService: ReproductionService,

  ) { }

  ngOnInit(): void {
    this.initializeData();
    this.userName = this.authService.getUserName()!;
  }

  public navegarSiguiente(): void {
    this.page++;
    this.getUserReproductions();

}


public navegarAnterior(): void {
    if (this.page > 0) {
        this.page--;
        this.getUserReproductions();
    }
}



  private handleError(error: any): void {
    console.log(error);
  }

  private initializeData(): void {
    this.token = this.authService.getToken();
    if (!this.token) {
      console.error('No se encontró el token de autenticación.');
      return;
    }

    this.decodedToken = this.authService.decodeToken(this.token);
    if (!this.decodedToken?.sub) {
      console.error('No se pudo obtener el nombre de usuario del token.');
      return;
    }

    this.isAuthenticated = true;
    this.getUserData();
    this.getUserReproductions();
  }

  private getUserData(): void {
    const username = this.decodedToken.sub;
    this.userService.getUserByUserName(username).subscribe({
      next: (userData: any) => {
        console.log('Datos del usuario:', userData);
        this.userData = userData;
      },
      error: (error) => {
        console.error('Error al obtener los datos del usuario:', error);
      }
    });
  }

  private getUserReproductions(): void {
    this.reproductionService.getReproductionsByUserName(this.decodedToken.sub, this.page, this.size, this.sort).subscribe({
      next: (reproductions: any[]) => {
        console.log('Reproducciones del usuario:', reproductions);
        this.reproductions = reproductions;
      },
      error: (error) => {
        console.error('Error al obtener las reproducciones del usuario:', error);
        console.error(this.decodedToken.sub);
      }
    });
  }
  

  updateUser(): void {
    if (!this.userData) {
      return;
    }
    this.userService.updateUser(this.userData).subscribe({
      next: (updatedUser: any) => {
        console.log("Usuario actualizado:", updatedUser);
        this.router.navigate(['../hello']);
      },
      error: (error) => {
        console.error("Error al actualizar el usuario:", error);
      }
    });
  }

  return(): void {
    this.router.navigate(['../']);
  }
}