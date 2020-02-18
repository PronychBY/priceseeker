import { Component, OnInit } from '@angular/core';
import { AuthService } from '../sign-in/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  username: string;
  password : string;
  name : string;
  email : string;
  role : string;
  datas: any;

  constructor(private authenticationService:AuthService) { }

  ngOnInit() {
  }

  signUp(){
    //this.authenticationService.signUpNN(this.username, this.password, this.email, this.name, this.role);
      console.log("calling sign up----");

      this.authenticationService.signUpNN(this.username, this.password, this.email, this.name, this.role)
      .subscribe((data) => this.datas = data,
      (error) =>{
          console.log(error);
      });

      console.log(" end sign up----"+this.datas);

  

    }
}



