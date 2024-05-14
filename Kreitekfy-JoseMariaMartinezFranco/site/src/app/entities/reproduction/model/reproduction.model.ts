export class Reproduction {
    id: number | undefined;
    userName: string;
    song: string;
    date: Date;
  
    constructor(
        id: number | undefined,
        userName: string,
        song: string,
        date: Date,

    ) {
        this.id = id;
        this.userName = userName;
        this.song = song;
        this.date = date;
    }
  
    public getId(): number | undefined {
        return this.id;
    }
  
    public setId(id: number): void {
        this.id = id;
    }
  
    public getUserName(): string {
        return this.userName;
    }
  
    public setUserName(userName: string): void {
        this.userName = userName;
    }
  
    public getSong(): string {
        return this.song;
    }
  
    public setSong(song: string): void {
        this.song = song;
    }
  
    public getDate(): Date {
        return this.date;
    }
  
    public setDate(date: Date): void {
        this.date = date;
    }
  }