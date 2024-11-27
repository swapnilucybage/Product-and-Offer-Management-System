import { Component, OnInit } from '@angular/core';
import { ProductOfferDTO } from '../../models/product-offer-dto';
import { ProductOfferService } from '../../services/product-offer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-offer-list',
  templateUrl: './product-offer-list.component.html',
  styleUrls: ['./product-offer-list.component.css']
})
export class ProductOfferListComponent implements OnInit {
  productOffers: ProductOfferDTO[] = [];
  paginatedProductOffers: ProductOfferDTO[] = [];
  
  currentPage: number = 1; // Current page for pagination
  itemsPerPage: number = 4; // Number of items per page

  constructor(private productOfferService: ProductOfferService, private router: Router) {}

  ngOnInit(): void {
    this.loadProductOffers();
  }

  loadProductOffers(): void {
    this.productOfferService.getAllProductOffers().subscribe(
      (data) => {
        this.productOffers = data;
        this.updatePaginatedProductOffers();
      },
      (error) => {
        console.error('Error fetching product offers', error);
      }
    );
  }

  updatePaginatedProductOffers(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    this.paginatedProductOffers = this.productOffers.slice(startIndex, startIndex + this.itemsPerPage);
  }

  viewDetails(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate(['/product-offer-details', id]);
    } else {
      console.error('Invalid product offer ID');
    }
  }

  editProductOffer(id: number | undefined): void {
    if (id !== undefined) {
      this.router.navigate(['/edit-product-offer', id]);
    } else {
      console.error('Invalid product offer ID');
    }
  }

  deleteProductOffer(id: number | undefined): void {
    if (id !== undefined && confirm('Are you sure you want to delete this product offer?')) {
      this.productOfferService.deleteProductOffer(id).subscribe(
        () => {
          this.loadProductOffers(); // Refresh the list after deletion
        },
        (error) => {
          console.error('Error deleting product offer', error);
        }
      );
    } else {
      console.error('Invalid product offer ID');
    }
  }

  createProductOffer(): void {
    this.router.navigate(['/create-product-offer']);
  }

  totalPages(): number {
    return Math.ceil(this.productOffers.length / this.itemsPerPage);
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages()) {
      this.currentPage++;
      this.updatePaginatedProductOffers();
    }
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePaginatedProductOffers();
    }
  }
}
