import { Component, OnInit } from '@angular/core';
import { ProductDetailsDto } from '../../models/product-details-dto';
import { ProductService } from '../../services/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: ProductDetailsDto[] = [];
  paginatedProducts: ProductDetailsDto[] = [];
  
  currentPage: number = 1; // Current page for pagination
  itemsPerPage: number = 4; // Number of items per page

  constructor(private productService: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe(
      (data) => {
        this.products = data;
        this.updatePaginatedProducts();
      },
      (error) => {
        console.error('Error fetching products', error);
      }
    );
  }

  updatePaginatedProducts(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    this.paginatedProducts = this.products.slice(startIndex, startIndex + this.itemsPerPage);
  }

  viewDetails(id: number): void {
    this.router.navigate(['/product-details', id]);
  }

  editProduct(id: number): void {
    this.router.navigate(['/edit-product', id]);
  }

  deleteProduct(id: number): void {
    if (confirm('Are you sure you want to delete this product?')) {
      this.productService.deleteProduct(id).subscribe(
        () => {
          this.loadProducts(); // Refresh the list after deletion
        },
        (error) => {
          console.error('Error deleting product', error);
        }
      );
    }
  }

  createProduct(): void {
    this.router.navigate(['/create-product']);
  }

  totalPages(): number {
    return Math.ceil(this.products.length / this.itemsPerPage);
  }

  nextPage() {
    if (this.currentPage < this.totalPages()) {
      this.currentPage++;
      this.updatePaginatedProducts();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePaginatedProducts();
    }
  }
}
