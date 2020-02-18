import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../sign-in/auth.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private _httpService: HttpClient,
    private authenticationService:AuthService,
    private http: HttpClient,
    private router:Router
  ) { }
}
