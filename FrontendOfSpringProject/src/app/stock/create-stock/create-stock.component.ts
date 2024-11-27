import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StockService } from 'src/app/services/stock.service';
import { ProductService } from 'src/app/services/product.service'; // Assuming you have this service

@Component({
  selector: 'app-create-stock',
  templateUrl: './create-stock.component.html',
  styleUrls: ['./create-stock.component.css']
})
export class CreateStockComponent {
  stockCount: number = 0;
  productCode: string = '';
  productId: number = 0; // Assuming a single product code is associated with a single product

  constructor(private stockService: StockService, private productService: ProductService, private router: Router) {}

  onSubmit(): void {
    // Fetch product by code to get the productId
    this.productService.getProductByCode(this.productCode).subscribe(
      (product) => {
        // Assuming 'product' contains the ID field
        this.productId = product.id;

        // Create StockDetailsDTO
        const stockDetailsDto = {
          id: 0,  // New stock
          stockCount: this.stockCount,
          products: [{ id: this.productId }]  // Associating the stock with the product by ID
        };

        // Now save stock using the service
        this.stockService.saveOrUpdateStock(stockDetailsDto).subscribe(
          () => {
            window.alert('Stock created/updated successfully!');
            this.router.navigate(['/stock']); // Redirect to stock list or another page
          },
          (error) => {
            console.error('Error creating stock', error);
            window.alert('Failed to add stock. Please try again.');
          }
        );
      },
      (error) => {
        console.error('Product not found:', error);
        window.alert('Product not found for the provided product code');
      }
    );
  }
}
