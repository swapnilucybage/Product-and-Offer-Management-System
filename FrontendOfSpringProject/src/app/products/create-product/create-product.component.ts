import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductDetailsDto } from 'src/app/models/product-details-dto';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent {

  product: ProductDetailsDto = {
    id: 0,
    code: '',
    name: '',
    category: '',
    mfgDate: '',
    expDate: '',
    stockCount: 0,
    productOffers: []
  };

  constructor(private productService: ProductService, private router: Router) {}

  onSubmit(): void {
    if (this.isConsumableWithInvalidDates()) {
      window.alert('For CONSUMABLE products, the expiry date cannot be earlier than the manufacturing date.');
      return;
    }

    this.productService.saveOrUpdateProduct(this.product).subscribe(
      () => {
        window.alert('Product added successfully!');
        this.router.navigate(['/products']);
      },
      (error) => {
        console.error('Error creating product', error);
        window.alert('Failed to add product. Please try again.');
      }
    );
  }

  private isConsumableWithInvalidDates(): boolean {
    if (this.product.category === 'CONSUMABLE' && this.product.expDate < this.product.mfgDate) {
      return true;
    }
    return false;
  }
}
