import { Component, OnInit } from '@angular/core';
import { OfferDetailsDto } from '../../models/offer-details-dto';
import { OfferService } from '../../services/offer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-offer-list',
  templateUrl: './offer-list.component.html',
  styleUrls: ['./offer-list.component.css']
})
export class OfferListComponent implements OnInit {
  offers: OfferDetailsDto[] = [];
  paginatedOffers: OfferDetailsDto[] = [];
  
  currentPage: number = 1;
  itemsPerPage: number = 4;

  constructor(private offerService: OfferService, private router: Router) {}

  ngOnInit(): void {
    this.loadOffers();
  }

  loadOffers(): void {
    this.offerService.getAllOffers().subscribe(
      (data) => {
        this.offers = data;
        this.updatePaginatedOffers();
      },
      (error) => {
        console.error('Error fetching offers', error);
      }
    );
  }

  updatePaginatedOffers(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    this.paginatedOffers = this.offers.slice(startIndex, startIndex + this.itemsPerPage);
  }

  viewDetails(id: number): void {
    this.router.navigate(['/offer-details', id]);
  }

  editOffer(id: number): void {
    this.router.navigate(['/edit-offer', id]);
  }

  deleteOffer(id: number): void {
    if (confirm('Are you sure you want to delete this offer?')) {
      this.offerService.deleteOffer(id).subscribe(
        () => {
          this.loadOffers();
        },
        (error) => {
          console.error('Error deleting offer', error);
        }
      );
    }
  }

  createOffer(): void {
    this.router.navigate(['/create-offer']);
  }

  totalPages(): number {
    return Math.ceil(this.offers.length / this.itemsPerPage);
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages()) {
      this.currentPage++;
      this.updatePaginatedOffers();
    }
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePaginatedOffers();
    }
  }
}
