import { Component, OnInit } from '@angular/core';
import { ProductDetailsDto } from '../models/product-details-dto';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  products: ProductDetailsDto[] = [];

  constructor(private productService: ProductService){}

  ngOnInit(): void {
      this.loadProducts();
  }

  loadProducts(){
    this.productService.getAllProducts().subscribe(
      (data) => {
        this.products = data;
      },
      (error) => {
        console.error ('Error fetching products', error);
      }
    );
  }
}
