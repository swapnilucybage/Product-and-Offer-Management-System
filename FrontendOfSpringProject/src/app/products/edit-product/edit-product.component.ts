import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { ProductDetailsDto } from '../../models/product-details-dto';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  editProductForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) {
    this.editProductForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      code: ['', [Validators.required, Validators.pattern(/^[A-Z0-9]{4,10}$/)]],
      category: ['', Validators.required],
      mfgDate: ['', Validators.required],
      expDate: ['', Validators.required],
      stockCount: ['', [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit(): void {
    const productId = +this.route.snapshot.paramMap.get('id')!;
    if (productId) {
      this.loadProduct(productId);
    }
  }

  loadProduct(id: number): void {
    this.productService.getProductById(id).subscribe(
      (data) => {
        this.editProductForm.patchValue(data); // Populate form with product data
      },
      (error) => {
        console.error('Error fetching product details', error);
      }
    );
  }

  updateProduct(): void {
    if (this.editProductForm.valid) {
      const updatedProduct: ProductDetailsDto = this.editProductForm.value;
      updatedProduct.id = +this.route.snapshot.paramMap.get('id')!;

      this.productService.saveOrUpdateProduct(updatedProduct).subscribe(
        () => {
          alert('Product updated successfully!');
          this.router.navigate(['/products']);
        },
        (error) => {
          console.error('Error updating product', error);
          alert('Failed to update product');
        }
      );
    } else {
      this.editProductForm.markAllAsTouched(); // Mark all fields as touched to show validation errors
    }
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }

  // Getter methods for easy access to form controls in the template
  get name() { return this.editProductForm.get('name'); }
  get code() { return this.editProductForm.get('code'); }
  get category() { return this.editProductForm.get('category'); }
  get mfgDate() { return this.editProductForm.get('mfgDate'); }
  get expDate() { return this.editProductForm.get('expDate'); }
}
