import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockDetailsDto } from '../models/stock-details-dto';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private apiUrl = 'http://localhost:8080/api/stock'; // Update with actual backend URL

  constructor(private http: HttpClient) {}

  getAllStocks(): Observable<StockDetailsDto[]> {
    return this.http.get<StockDetailsDto[]>(`${this.apiUrl}`);
  }

  deleteStock(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  saveOrUpdateStock(stockDetailsDto: StockDetailsDto): Observable<StockDetailsDto> {
    return this.http.post<StockDetailsDto>(this.apiUrl, stockDetailsDto);
  }

  // Fetch stock by product code (you can use this method if needed)
  getStockByProductCode(productCode: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/product/${productCode}`);
  }

  // Get stock by ID
  getStockById(stockId: number): Observable<StockDetailsDto> {
    return this.http.get<StockDetailsDto>(`${this.apiUrl}/${stockId}`);
  }
  
}
