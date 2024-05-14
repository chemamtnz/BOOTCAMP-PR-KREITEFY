import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Song } from '../model/song.model';
import { SongService } from '../service/song.service';
import { Reproduction } from '../../reproduction/model/reproduction.model';
import { ReproductionService } from '../../reproduction/service/reproduction.service';
import { AuthService } from 'src/app/auth/service/auth.service';

@Component({
  selector: 'app-song-form',
  templateUrl: './song-form.component.html',
  styleUrls: ['./song-form.component.scss']
})
export class SongFormComponent implements OnInit {
  songId?: number;
  song?: Song;
  token?: string;
  decodedToken?: any;
  isAuthenticated = false;
  newValoration = 0;
  currentValoration = 0;
  stars: number[] = [1, 2, 3, 4];

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private songService: SongService,
    private router: Router,
    private reproductionService: ReproductionService,
  ) {}

  ngOnInit(): void {
    this.initialize();
  }

  private initialize(): void {
    this.getSongIdFromRoute();
    this.checkAuthentication();
  }

  private getSongIdFromRoute(): void {
    const songIdParam = this.route.snapshot.paramMap.get('songId');
    this.songId = songIdParam ? +songIdParam : undefined;
    if (this.songId) {
      this.getSongById(this.songId);
    }
  }

  private checkAuthentication(): void {
    this.token = this.authService.getToken();
    if (this.token) {
      this.decodedToken = this.authService.decodeToken(this.token);
      this.isAuthenticated = true;
    }
  }

  generateStars(valoration: number): number[] {
    return Array.from({ length: valoration }, (_, index) => index);
  }

  return(): void {
    this.router.navigate(['../']);
  }

  changeSongValoration(): void {
    if (!this.songId || !this.newValoration) {
      console.error('Invalid song ID or new valoration.');
      return;
    }

    this.songService.getSongById(this.songId).subscribe({
      next: (song: Song) => {
        song.valoration = this.newValoration;
        this.songService.updateSong(song).subscribe({
          next: () => {
            console.log('Song updated successfully.');
            this.currentValoration = this.newValoration;
          },
          error: (error) => {
            console.error('Error updating song:', error);
          }
        });
      },
      error: (error) => {
        console.error('Error fetching song:', error);
      }
    });
  }

  selectValoration(star: number): void {
    this.newValoration = star;
    this.changeSongValoration();
  }

  registerReproduction(): void {
    if (!this.song || !this.token) {
      console.error('Invalid song or token.');
      return;
    }

    this.song.reproductions = this.song.reproductions ? this.song.reproductions + 1 : 1;
    const reproduction = new Reproduction(
      10,
      this.decodedToken?.sub,
      this.song.name,
      new Date()
    );

    this.songService.updateSong(this.song).subscribe({
      next: () => {
        console.log('Reproductions registered successfully.');
      },
      error: (error) => {
        console.error('Error registering reproductions:', error);
      }
    });

    this.reproductionService.insertReproduction(reproduction).subscribe({
      next: () => {
        console.log('Reproduction registered successfully.');
      },
      error: (error) => {
        console.error('Error registering reproduction:', error);
      }
    });
  }

  private getSongById(songId: number): void {
    this.songService.getSongById(songId).subscribe({
      next: (song: Song) => {
        this.song = song;
        this.currentValoration = song.valoration;
      },
      error: (error) => {
        console.error('Error fetching song:', error);
      }
    });
  }
}
