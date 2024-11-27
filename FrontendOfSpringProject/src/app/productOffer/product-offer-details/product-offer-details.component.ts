import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductOfferService } from '../../services/product-offer.service';
import { ProductOfferDTO } from '../../models/product-offer-dto';

@Component({
  selector: 'app-product-offer-details',
  templateUrl: './product-offer-details.component.html',
  styleUrls: ['./product-offer-details.component.css']
})
export class ProductOfferDetailsComponent implements OnInit {
  productOffer: ProductOfferDTO | null = null;

  constructor(
    private route: ActivatedRoute,
    private productOfferService: ProductOfferService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.productOfferService.getProductOfferById(id).subscribe(
        (data) => (this.productOffer = data),
        (error) => console.error('Error fetching product offer details:', error)
      );
    }
  }
}
