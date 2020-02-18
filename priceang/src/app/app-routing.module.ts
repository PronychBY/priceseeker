import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PriceComponent } from './price/price.component';
import { AboutComponent } from './about/about.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ProductComponent } from './product/product.component';
import { UserComponent } from './user/user.component';


const routes: Routes = [
  { path: '', component: PriceComponent},
  { path: 'price', component: PriceComponent},
  { path: 'product', component: ProductComponent},
  { path: 'about', component: AboutComponent},
  { path: 'sign-in', component: SignInComponent},
  { path: 'sign-up', component: SignUpComponent},
  { path: 'users', component: UserComponent},
  { path: '**', redirectTo:'#',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
