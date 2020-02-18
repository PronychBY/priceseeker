import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // BASE_PATH: 'http://localhost:8080'
  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'

  public username: String;
  public password: String;
  public name: String;
  public email: String;
  public role: String;
  public ff;
  constructor(private http: HttpClient,private router:Router) { }

  authenticationService(username: String, password: String) {
    return this.http.post<any>(`http://localhost:8080/controller/api/auth/signin`,{ username, password })
    .pipe(map(user => {
      // store user details and jwt token in local storage to keep user logged in between page refreshes
      localStorage.setItem('currentUser', JSON.stringify(user));
      localStorage.setItem('token', user.accessToken);
      this.username = username;
      this.password = password;
      this.registerSuccessfulLogin(username);
      console.log("ls:"+localStorage.getItem('currentUser'));
      console.log("token:"+localStorage.getItem('token'));
      return user;
    }));    
  }
  
  getToken(){
    
    return "Bearer "+localStorage.getItem('token');
  }

  registerSuccessfulLogin(username) {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username)
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    this.username = null;
    this.password = null;
    localStorage.removeItem('token');
    
    this.router.navigate(['/sign-in']);

  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
  }
  
  isAdmin(user:User) {
    var arr = user.roles;
    for (var i = 0; i < arr.length; i++) {
      if (arr[i]=="ROLE_ADMIN") return true;
    }    
    return false
  }

  getLoggedInUserName() {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return ''
    return user
  }


  signUpNN(username:String ,password:String ,name:String ,email:String ,role:String){
  //signUpNN(){
    
    //console.log("signup begin "+username +" "+name+" "+password+" "+email+" "+role);
    //return this.http.get<any>(`http://localhost:8080/controller/product/list`);
    //return this.http.get("http://jsonplaceholder.typicode.com/users");
    //return this.http.post<any>(`http://localhost:8080/controller/api/auth/test`,{ name, username, email, password });
    //return this.http.post<any>(`http://localhost:8080/controller/api/auth/test`,{name, username, email, password });
    return this.http.post<any>(`http://localhost:8080/controller/api/auth/signup`,{name, username, email, password });
  }

  getUsers() {
    console.log("Inside getUsers():::::")
    return this.http.get<any>(`http://localhost:8080/controller/user/list`);    
    console.log("end of getUsers():::::");
}    


}
