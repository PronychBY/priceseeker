import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Price } from './price';
import { AuthService } from '../sign-in/auth.service';

@Injectable()
export class PriceService{
    
    constructor(
        private _httpService: HttpClient,
        private authenticationService:AuthService
        ){}

    getAllPrices(): Observable<any>{
        console.log("inside the service getAllPrices():::::::");
        if (this.authenticationService.isUserLoggedIn())
            return this._httpService.get("http://localhost:8080/controller/price/list",
            { headers: { authorization: this.authenticationService.getToken()} }); 
        
    }

    addPrice(price: Price){
      let body = JSON.parse(JSON.stringify(price));
      return this._httpService.post("http://localhost:8080/controller/price/addcurentdate", body,
      { headers: { authorization: this.authenticationService.getToken()} });
    }
  


}