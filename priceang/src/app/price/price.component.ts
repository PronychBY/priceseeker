import{Component, OnInit} from '@angular/core';
import {Price} from './price';
import { PriceService } from './price.service';
import { AuthService } from '../sign-in/auth.service';
import { Router } from '@angular/router';
@Component({
    selector : 'app-price',
    templateUrl : './price.component.html',
    styleUrls : ['./price.component.css']
})

export class PriceComponent implements OnInit{
    prices: Price[];
    price = new Price();
    constructor(private _priceService: PriceService, private authenticationService:AuthService,private router:Router){}

    ngOnInit(): void {
        console.log("calling ngOnInit()::::");
        if (!this.authenticationService.isUserLoggedIn()) {
            this.router.navigate(['/sign-in']);
        }
        else {
            this.getPrices();
        }
        
    }    
    getPrices(): void{
        console.log("Inside getPrices():::::")
        this._priceService.getAllPrices()
            .subscribe((data) => this.prices = data,
            (error) =>{
                console.log(error);
            }
        );

        console.log("end of getPrices():::::");
    }    
    
    addPrice(): void{
        console.log("inside the addProduct()::::::")
        
        this._priceService.addPrice(this.price)
            .subscribe((response) => {console.log(response); this.getPrices(); },
            (error) =>{
                console.log(error);
                //this.statusMessage = "Problem with service. Please try again later!";
            }
        );  
        console.log("end of addPrice()::::");
        this.router.navigate(['/prices']);
      }  
    

}