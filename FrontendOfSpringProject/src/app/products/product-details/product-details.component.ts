import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { ProductDetailsDto } from '../../models/product-details-dto';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  product: ProductDetailsDto | undefined;

  constructor(private route: ActivatedRoute, private productService: ProductService,  private router: Router) {}

  ngOnInit(): void {
    const productId = +this.route.snapshot.paramMap.get('id')!;
    if (productId) {
      this.loadProduct(productId);
    }
  }

  loadProduct(id: number): void {
    this.productService.getProductById(id).subscribe(
      (data) => {
        this.product = data;
      },
      (error) => {
        console.error('Error fetching product details', error);
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/products']); // Replace '/stock' with the route of your stock list component
  }
}
