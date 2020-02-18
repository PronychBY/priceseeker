import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {PriceComponent} from './price/price.component';
import { PriceService } from './price/price.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ProductComponent } from './product/product.component';
import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent, PriceComponent, AboutComponent, HomeComponent, SignInComponent, SignUpComponent, ProductComponent, UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule    
  ],
  providers: [PriceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
