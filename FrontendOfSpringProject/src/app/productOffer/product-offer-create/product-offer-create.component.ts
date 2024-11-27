import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductOfferDTO } from '../../models/product-offer-dto';
import { ProductOfferService } from '../../services/product-offer.service';

@Component({
  selector: 'app-product-offer-create',
  templateUrl: './product-offer-create.component.html',
  styleUrls: ['./product-offer-create.component.css'],
})
export class ProductOfferCreateComponent implements OnInit {
  productOffer: ProductOfferDTO = new ProductOfferDTO();
  productCodes: string[] = [];
  offerCodes: string[] = [];

  constructor(
    private productOfferService: ProductOfferService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProductCodes();
    this.loadOfferCodes();
  }

  loadProductCodes(): void {
    this.productOfferService.getProductCodes().subscribe(
      (codes) => {
        this.productCodes = codes;
      },
      (error) => {
        console.error('Error fetching product codes', error);
      }
    );
  }

  loadOfferCodes(): void {
    this.productOfferService.getOfferCodes().subscribe(
      (codes) => {
        this.offerCodes = codes;
      },
      (error) => {
        console.error('Error fetching offer codes', error);
      }
    );
  }

  createProductOffer(form: any): void {
    if (form.valid) {
      const startDate = new Date(this.productOffer.offerStartDate);
      const endDate = new Date(this.productOffer.offerEndDate);

      if (endDate < startDate) {
        alert('Error: End Date cannot be earlier than Start Date.');
        return; // Stop form submission
      }

      this.productOfferService.createProductOffer(this.productOffer).subscribe(
        () => {
          alert('Product Offer created successfully!');
          this.router.navigate(['/product-offer']);
        },
        (error) => {
          console.error('Error creating product offer', error);
        }
      );
    }
  }
}
