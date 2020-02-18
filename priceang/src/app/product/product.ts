import { Category } from './category';

export class Product{
    id: string;
    name: string;
    category: Category;
    
    constructor(){
        console.log("New Product():::::")
        
    }
}