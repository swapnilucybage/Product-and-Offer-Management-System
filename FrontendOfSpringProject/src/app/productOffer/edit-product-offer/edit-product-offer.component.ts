import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductOfferDTO } from '../../models/product-offer-dto';
import { ProductOfferService } from '../../services/product-offer.service';

@Component({
  selector: 'app-edit-product-offer',
  templateUrl: './edit-product-offer.component.html',
  styleUrls: ['./edit-product-offer.component.css']
})
export class EditProductOfferComponent implements OnInit {
  editProductOfferForm: FormGroup;
  productCodes: string[] = [];
  offerCodes: string[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private productOfferService: ProductOfferService,
    private router: Router
  ) {
    this.editProductOfferForm = this.formBuilder.group({
      productCode: ['', Validators.required],
      offerCode: ['', Validators.required],
      offerStartDate: ['', Validators.required],
      offerEndDate: ['', [Validators.required, this.validateDates.bind(this)]]
    });
  }

  ngOnInit(): void {
    const offerId = +this.route.snapshot.paramMap.get('id')!;
    if (offerId) {
      this.loadProductOffer(offerId);
    }
    this.loadProductCodes();
    this.loadOfferCodes();
  }

  loadProductOffer(id: number): void {
    this.productOfferService.getProductOfferById(id).subscribe(
      (data) => {
        this.editProductOfferForm.patchValue(data); // Populate form with offer data
      },
      (error) => {
        console.error('Error fetching product offer details', error);
      }
    );
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

  updateProductOffer(): void {
    if (this.editProductOfferForm.valid) {
      const updatedOffer: ProductOfferDTO = this.editProductOfferForm.value;
      updatedOffer.id = +this.route.snapshot.paramMap.get('id')!;

      this.productOfferService.updateProductOffer(updatedOffer.id!, updatedOffer).subscribe(
        () => {
          alert('Product Offer updated successfully!');
          this.router.navigate(['/product-offer']);
        },
        (error) => {
          console.error('Error updating product offer', error);
          alert('Failed to update product offer.');
        }
      );
    } else {
      this.editProductOfferForm.markAllAsTouched(); // Mark all fields as touched to show validation errors
    }
  }

  cancel(): void {
    this.router.navigate(['/product-offer']);
  }

  // Custom validator to check if offerEndDate is earlier than offerStartDate
  validateDates(control: any): { [key: string]: boolean } | null {
    const startDate = this.editProductOfferForm?.get('offerStartDate')?.value;
    const endDate = control.value;

    if (startDate && endDate && new Date(endDate) < new Date(startDate)) {
      return { dateMismatch: true };
    }
    return null;
  }

  // Getter methods for easy access to form controls in the template
  get productCode() { return this.editProductOfferForm.get('productCode'); }
  get offerCode() { return this.editProductOfferForm.get('offerCode'); }
  get offerStartDate() { return this.editProductOfferForm.get('offerStartDate'); }
  get offerEndDate() { return this.editProductOfferForm.get('offerEndDate'); }
}
