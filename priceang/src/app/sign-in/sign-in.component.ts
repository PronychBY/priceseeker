import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { User } from './user';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  users: User[];
  username: string;
  password : string;
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;


  constructor(
    private route:ActivatedRoute,
    private router:Router,
    private authenticationService:AuthService
  ) { }

  ngOnInit() {
    console.log("calling ngOnInitUser()::::");
    this.getAllUsers();

  }
  
  testLogin(){
    console.log("calling handle login----"+this.authenticationService.getLoggedInUserName());
  }
  test2Login(){
    console.log("calling2 handle login----"+this.authenticationService.isUserLoggedIn());
  }
  
  handleLogin(){
    console.log("calling handle login----");
    
    this.authenticationService.authenticationService(this.username, this.password).subscribe((result)=> {
      console.log("calling handle login----"+result.accessToken);
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['/about']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });        }

    getAllUsers(): void{
      console.log("inside the service getAllUsers():::::::");
      this.authenticationService.getUsers()
      .subscribe((data) => this.users = data,
        (error) =>{
          console.log(error);
        }
      );

  }
}
