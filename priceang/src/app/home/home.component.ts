import { Component, OnInit } from '@angular/core';
import { AuthService } from '../sign-in/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  authenticated:boolean;
  constructor(private authenticationService:AuthService) { }

  ngOnInit() {
    this.authenticated = this.authenticationService.isUserLoggedIn();
  }
 
  logout(){
    console.log("calling logout----");
    this.authenticationService.logout();
    this.authenticated = false;
  }
}
