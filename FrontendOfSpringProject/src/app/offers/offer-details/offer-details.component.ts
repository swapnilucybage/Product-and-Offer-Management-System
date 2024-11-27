import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferService } from '../../services/offer.service';
import { OfferDetailsDto } from 'src/app/models/offer-details-dto';

@Component({
  selector: 'app-offer-details',
  templateUrl: './offer-details.component.html',
  styleUrls: ['./offer-details.component.css']
})
export class OfferDetailsComponent implements OnInit {
  offer: OfferDetailsDto | undefined;

  constructor(private route: ActivatedRoute, private offerService: OfferService, private router: Router) {}

  ngOnInit(): void {
    const offerId = +this.route.snapshot.paramMap.get('id')!;
    if (offerId) {
      this.loadOffer(offerId);
    }
  }

  loadOffer(id: number): void {
    this.offerService.getOfferById(id).subscribe(
      (data) => {
        this.offer = data;
      },
      (error) => {
        console.error('Error fetching offer details', error);
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/offer']); // Replace '/stock' with the route of your stock list component
  }
}
