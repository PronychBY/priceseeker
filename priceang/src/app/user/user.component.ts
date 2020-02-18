import { Component, OnInit } from '@angular/core';
import { User } from '../sign-in/user';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../sign-in/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users: User[];
  constructor(
    private route:ActivatedRoute,
    private router:Router,
    private authenticationService:AuthService
  ) { }

  ngOnInit() {
    this.getAllUsers();
  }
  
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
