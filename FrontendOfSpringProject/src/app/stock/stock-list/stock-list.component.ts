import { Component, OnInit } from '@angular/core';
import { StockDetailsDto } from '../../models/stock-details-dto'; // Define this DTO model
import { StockService } from '../../services/stock.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-stock-list',
  templateUrl: './stock-list.component.html',
  styleUrls: ['./stock-list.component.css']
})
export class StockListComponent implements OnInit {
  stocks: StockDetailsDto[] = [];
  paginatedStocks: StockDetailsDto[] = [];

  currentPage: number = 1; // Current page for pagination
  itemsPerPage: number = 4; // Number of items per page

  constructor(private stockService: StockService, private router: Router) {}

  ngOnInit(): void {
    this.loadStocks();
  }

  loadStocks(): void {
    this.stockService.getAllStocks().subscribe(
      (data) => {
        this.stocks = data;
        this.updatePaginatedStocks();
      },
      (error) => {
        console.error('Error fetching stocks', error);
      }
    );
  }

  updatePaginatedStocks(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    this.paginatedStocks = this.stocks.slice(startIndex, startIndex + this.itemsPerPage);
  }

  viewStockDetails(id: number): void {
    this.router.navigate(['/stock-details', id]);
  }

  editStock(id: number): void {
    this.router.navigate(['/edit-stock', id]);
  }

  deleteStock(id: number): void {
    if (confirm('Are you sure you want to delete this stock?')) {
      this.stockService.deleteStock(id).subscribe(
        () => {
          this.loadStocks(); // Refresh the list after deletion
        },
        (error) => {
          console.error('Error deleting stock', error);
        }
      );
    }
  }

  addStock(): void {
    this.router.navigate(['/create-stock']);
  }

  totalPages(): number {
    return Math.ceil(this.stocks.length / this.itemsPerPage);
  }

  nextPage() {
    if (this.currentPage < this.totalPages()) {
      this.currentPage++;
      this.updatePaginatedStocks();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePaginatedStocks();
    }
  }
}
