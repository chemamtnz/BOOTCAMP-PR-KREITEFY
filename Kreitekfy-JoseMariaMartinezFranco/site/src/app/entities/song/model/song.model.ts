export class Song {
  id: number | undefined;
  image: string;
  style: string;
  artist: string;
  album: string;
  name: string;
  duration: number;
  reproductions: number;
  valoration: number;

  constructor(
      id: number | undefined,
      image: string,
      style: string,
      artist: string,
      album: string,
      name: string,
      duration: number,
      reproductions: number,
      valoration: number
  ) {
      this.id = id;
      this.image = image;
      this.style = style;
      this.artist = artist;
      this.album = album;
      this.name = name;
      this.duration = duration;
      this.reproductions = reproductions;
      this.valoration = valoration;
  }

  public getId(): number | undefined {
      return this.id;
  }

  public setId(id: number): void {
      this.id = id;
  }

  public getImage(): string {
      return this.image;
  }

  public setImage(image: string): void {
      this.image = image;
  }

  public getStyle(): string {
      return this.style;
  }

  public setStyle(style: string): void {
      this.style = style;
  }

  public getArtist(): string {
      return this.artist;
  }

  public setArtist(artist: string): void {
      this.artist = artist;
  }

  public getAlbum(): string {
      return this.album;
  }

  public setAlbum(album: string): void {
      this.album = album;
  }

  public getName(): string {
      return this.name;
  }

  public setName(name: string): void {
      this.name = name;
  }

  public getDuration(): number {
      return this.duration;
  }

  public setDuration(duration: number): void {
      this.duration = duration;
  }

  public getReproductions(): number {
      return this.reproductions;
  }

  public setReproductions(reproductions: number): void {
      this.reproductions = reproductions;
  }

  public getValoration(): number {
      return this.valoration;
  }

  public setValoration(valoration: number): void {
      this.valoration = valoration;
  }
}
