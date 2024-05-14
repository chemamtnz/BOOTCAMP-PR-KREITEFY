export class User {
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    email: string;
    role: string;

    constructor(
        username: string,
        password: string,
        firstName: string,
        lastName: string,
        email: string,
        role: string,
    ) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;

    }
  
    public getUsername(): string {
        return this.username;
    }
  
    public setUsername(username: string): void {
        this.username = username;
    }
  
    public getPassowrd(): string {
        return this.password;
    }
  
    public setPassowrd(password: string): void {
        this.password = password;
    }
  
    public getFirstName(): string {
        return this.firstName;
    }
  
    public setFirstName(firstName: string): void {
        this.firstName = firstName;
    }

    public getLastName(): string {
        return this.lastName;
    }
  
    public setLastName(lastName: string): void {
        this.lastName = lastName;
    }
  
    public getEmail(): string {
        return this.email;
    }
  
    public setEmail(email: string): void {
        this.email = email;
    }
  
    public getRole(): string {
        return this.role;
    }
  
    public setRole(role: string): void {
        this.role = role;
    }

}