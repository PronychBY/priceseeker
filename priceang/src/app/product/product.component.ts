import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductService } from './product.service';
import { AuthService } from '../sign-in/auth.service';
import { Router } from '@angular/router';
import { Category } from './category';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  products: Product[];
  product = new Product();
  categories: Category[];
  category = new Category();
  
  constructor(
    private _productService: ProductService, 
    private authenticationService:AuthService,
    private router:Router
    ){}

  ngOnInit(): void {
      console.log("calling ngOnInit()::::");
      if (!this.authenticationService.isUserLoggedIn()) {
          this.router.navigate(['/sign-in']);
      }
      else {
        this.getProducts();
        this.getCategories();
      }
      
  }    
  getProducts(): void{
      console.log("Inside getProducts():::::")
      this._productService.getAllProducts()
          .subscribe((data) => this.products = data,
          (error) =>{
              console.log(error);
          }
      );

      console.log("end of getProducts():::::");
  }    

    deleteProduct(productId: string){
      console.log("Inside the deleteProduct()::::productId::::"+productId);
      this._productService.deleteProduct(productId)
          .subscribe((response) => {console.log(response);this.getProducts();},
          (error) =>{
              console.log(error);
              //this.statusMessage = "Problem with service. Please try again later!";
          });
          
          this.reset();
          console.log("end of deleteProduct():::::::");
  }

  getProduct(productId: string){
      console.log("Inside the updateProduct()::::::productId::::"+productId);
      this._productService.getProductById(productId)
          .subscribe((productData) => {this.product = productData; this.getProducts(); }),
          (error) => {
              console.log(error);
              //this.statusMessage = "Problem with service. Please try again later!";
          }
      this.reset();    
      console.log("end of updateProduct()::::::");
  }

  getCategories(){
    //getAllCategories
    console.log("Inside getCategories():::::")
    this._productService.getAllCategories()
        .subscribe((data) => this.categories = data,
        (error) =>{
            console.log(error);
        }
    );
    console.log("end of getCategories():::::");
  }
  
  private reset(){
    console.log("inside the reset():::::::");
    this.product.id = null;
    this.product.name = null;
    console.log("end of reset():::::::");
  }

  addProduct(): void{
    console.log("inside the addProduct()::::::")
    
    this._productService.addProduct(this.product, this.category)
        .subscribe((response) => {console.log("dd:"+response); this.getProducts(); this.reset();},
        (error) =>{
            console.log(error);
            //this.statusMessage = "Problem with service. Please try again later!";
        }
    );   
    console.log("end of addProduct()::::");
    this.router.navigate(['/product']);
  }  
  getByCategory(): void{
    console.log("Inside getByCategory():::::")
    this._productService.getByCategory(this.product.category.id,this.product.category.name)
        .subscribe((data) => this.products = data,
        (error) =>{
            console.log(error);
        }
    );
    console.log("end of getByCategory():::::");
  }    

}
