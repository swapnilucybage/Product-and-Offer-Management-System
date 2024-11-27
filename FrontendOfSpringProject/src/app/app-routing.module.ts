import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductDetailsComponent } from './products/product-details/product-details.component';
import { EditProductComponent } from './products/edit-product/edit-product.component';
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

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'product-details/:id', component: ProductDetailsComponent },
  { path: 'edit-product/:id', component: EditProductComponent},
  { path: 'create-product', component: CreateProductComponent},
  { path: 'offer', component: OfferListComponent},
  { path: 'offer-details/:id', component: OfferDetailsComponent},
  { path: 'edit-offer/:id', component: EditOfferComponent},
  { path: 'create-offer', component: CreateOfferComponent},
  { path: 'stock', component: StockListComponent},
  { path: 'create-stock', component: CreateStockComponent},
  { path: 'stock-details/:id', component: StockDetailsComponent},
  { path: 'product-offer', component: ProductOfferListComponent},
  { path: 'product-offer-details/:id', component: ProductOfferDetailsComponent},
  { path: 'create-product-offer', component: ProductOfferCreateComponent},
  { path: 'edit-product-offer/:id', component: EditProductOfferComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
