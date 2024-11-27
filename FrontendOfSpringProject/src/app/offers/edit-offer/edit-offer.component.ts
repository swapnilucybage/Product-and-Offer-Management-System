import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferService } from '../../services/offer.service';
import { OfferDetailsDto } from '../../models/offer-details-dto';

@Component({
  selector: 'app-edit-offer',
  templateUrl: './edit-offer.component.html',
  styleUrls: ['./edit-offer.component.css']
})
export class EditOfferComponent implements OnInit {
  editOfferForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private offerService: OfferService,
    private router: Router
  ) {
    this.editOfferForm = this.formBuilder.group({
      code: ['', [Validators.required]],
      description: ['', [Validators.required]],
      value: ['', Validators.required],
      activationDate: ['', Validators.required],
      expiryDate: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const offerId = +this.route.snapshot.paramMap.get('id')!;
    if (offerId) {
      this.loadOffer(offerId);
    }
  }

  loadOffer(id: number): void {
    this.offerService.getOfferById(id).subscribe(
      (data) => {
        this.editOfferForm.patchValue(data); // Populate form with offer data
      },
      (error) => {
        console.error('Error fetching offer details', error);
      }
    );
  }

  updateOffer(): void {
    if (this.editOfferForm.valid) {
      const updatedOffer: OfferDetailsDto = this.editOfferForm.value;
      updatedOffer.id = +this.route.snapshot.paramMap.get('id')!;

      this.offerService.saveOrUpdateOffer(updatedOffer).subscribe(
        () => {
          alert('Offer updated successfully!');
          this.router.navigate(['/offer']);
        },
        (error) => {
          console.error('Error updating offer', error);
          alert('Failed to update offer');
        }
      );
    } else {
      this.editOfferForm.markAllAsTouched(); // Mark all fields as touched to show validation errors
    }
  }

  cancel(): void {
    this.router.navigate(['/offers']);
  }

  // Getter methods for easy access to form controls in the template
  get code() { return this.editOfferForm.get('code'); }
  get description() { return this.editOfferForm.get('description'); }
  get value() { return this.editOfferForm.get('value'); }
  get activationDate() { return this.editOfferForm.get('activationDate'); }
  get expiryDate() { return this.editOfferForm.get('expiryDate'); }
}
