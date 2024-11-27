// src/app/components/stock-details/stock-details.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StockService } from '../../services/stock.service';
import { StockDetailsDto } from '../../models/stock-details-dto';

@Component({
  selector: 'app-stock-details',
  templateUrl: './stock-details.component.html',
  styleUrls: ['./stock-details.component.css']
})
export class StockDetailsComponent implements OnInit {
  stockDetails: StockDetailsDto | null = null; // Initialize with null to handle loading state

  constructor(
    private route: ActivatedRoute,
    private stockService: StockService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Retrieve the stock ID from the route params
    const stockId = Number(this.route.snapshot.paramMap.get('id'));

    // Handle invalid stock ID
    if (isNaN(stockId)) {
      console.error('Invalid stock ID');
      return;
    }

    // Fetch stock details from the backend service
    this.stockService.getStockById(stockId).subscribe(
      (data) => {
        // Assign the fetched stock details to the stockDetails property
        this.stockDetails = data;
      },
      (error) => {
        // Handle errors
        console.error('Error fetching stock details', error);
        alert('Could not load stock details. Please try again later.');
      }
    );
  }
  goBack(): void {
    this.router.navigate(['/stock']); // Replace '/stock' with the route of your stock list component
  }
}
