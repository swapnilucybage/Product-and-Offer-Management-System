import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { EditProductComponent } from './products/edit-product/edit-product.component';
import {  FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateProductComponent } from './products/create-product/create-product.component';
import { OfferListComponent } from './offers/offer-list/offer-list.component';
import { OfferDetailsComponent } from './offers/offer-details/offer-details.component';
import { CreateOfferComponent } from './offers/create-offer/create-offer.component';
import { EditOfferComponent } from './offers/edit-offer/edit-offer.component';
import { StockListComponent } from './stock/stock-list/stock-list.component';
import { CreateStockComponent } from './stock/create-stock/create-stock.component';
import { StockDetailsComponent } from './stock/stock-details/stock-details.component';
import { ProductOfferListComponent } from './productOffer/product-offer-list/product-offer-list.component';
import { ProductOfferDetailsComponent } from './productOffer/product-offer-details/product-offer-details.component';
import { ProductOfferCreateComponent } from './productOffer/product-offer-create/product-offer-create.component';
import { EditProductOfferComponent } from './productOffer/edit-product-offer/edit-product-offer.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductListComponent,
    ProductDetailsComponent,
    EditProductComponent,
    CreateProductComponent,
    OfferListComponent,
    OfferDetailsComponent,
    CreateOfferComponent,
    EditOfferComponent,
    StockListComponent,
    CreateStockComponent,
    StockDetailsComponent,
    ProductOfferListComponent,
    ProductOfferDetailsComponent,
    ProductOfferCreateComponent,
    EditProductOfferComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
