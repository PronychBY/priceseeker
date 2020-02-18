import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../sign-in/auth.service';
import { Observable } from 'rxjs';
import { Product } from './product';
import { Category } from './category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor( 
    private _httpService: HttpClient,
    private authenticationService:AuthService
    ) { }

  getAllProducts(): Observable<any>{
    console.log("inside the service getAllProducts():::::::");
      
    if (this.authenticationService.isUserLoggedIn())
        return this._httpService.get("http://localhost:8080/controller/product/list",
        { headers: { authorization: this.authenticationService.getToken()} }); 
      
  }
  getByCategory(id:String, name:String): Observable<any>{
    console.log("inside the service getByCategory():::::::");
    
    const headers = new HttpHeaders({ authorization: this.authenticationService.getToken()});
    
    if (this.authenticationService.isUserLoggedIn())
        return this._httpService.post("http://localhost:8080/controller/product/bycategory",
        {id, name},
        {headers: { authorization: this.authenticationService.getToken()} }); 
      
  }
  
  
  getProductById(productId: string): Observable<any>{
    console.log("Inside the getProductById() service:::::id:"+productId);
    return this._httpService.get("http://localhost:8080/controller/product/get/"+productId,
      { headers: { authorization: this.authenticationService.getToken()} } );
  }
  deleteProduct(productId: string){
    console.log("Inside the service deleteProduct():::::product id:::"+productId);
    return this._httpService.delete("http://localhost:8080/controller/product/delete/"+productId,
    { headers: { authorization: this.authenticationService.getToken()} } );
  }


  getAllCategories(): Observable<any>{
    console.log("inside the service getAllCategories():::::::");
    if (this.authenticationService.isUserLoggedIn())
        return this._httpService.get("http://localhost:8080/controller/category/list",
        { headers: { authorization: this.authenticationService.getToken()} }); 
      
  }
  
  addProduct(product: Product, category:Category){
    let body = JSON.parse(JSON.stringify(product));
    //return this._httpService.post("http://localhost:8080/controller/product/add", body);
    
    if(product.id){    
      console.log("Inside addProduct update service():::::::");
      return this._httpService.put("http://localhost:8080/controller/product/update/"+product.id, body);
    }else{
      console.log("Inside addProduct add service():::::::");
      return this._httpService.post("http://localhost:8080/controller/product/add", body);
    };
  
  }
}
