import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OfferDetailsDto } from 'src/app/models/offer-details-dto';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-create-offer',
  templateUrl: './create-offer.component.html',
  styleUrls: ['./create-offer.component.css']
})
export class CreateOfferComponent {

  offer: OfferDetailsDto = {
    id: 0,
    code: '',
    description: '',
    value: '',
    status: 'PENDING', // Initial status as placeholder; will be set automatically by backend
    activationDate: '',
    expiryDate: ''
  };

  constructor(private offerService: OfferService, private router: Router) {}

  onSubmit(): void {
    if (this.offer.expiryDate < this.offer.activationDate) {
      window.alert('Expiry date cannot be earlier than the activation date.');
      return;
    }

    this.offerService.saveOrUpdateOffer(this.offer).subscribe(
      () => {
        window.alert('Offer created successfully!');
        this.router.navigate(['/offer']);
      },
      (error) => {
        console.error('Error creating offer', error);
        window.alert('Failed to create offer. Please try again.');
      }
    );
  }
}
