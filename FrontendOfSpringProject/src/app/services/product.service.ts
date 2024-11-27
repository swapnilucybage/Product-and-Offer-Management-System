import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductDetailsDto } from '../models/product-details-dto';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8080/api/products'; // Adjust the URL based on your Spring Boot backend

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<ProductDetailsDto[]> {
    return this.http.get<ProductDetailsDto[]>(this.apiUrl);
  }

  getProductById(id: number): Observable<ProductDetailsDto> {
    return this.http.get<ProductDetailsDto>(`${this.apiUrl}/${id}`);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  saveOrUpdateProduct(product: ProductDetailsDto): Observable<ProductDetailsDto> {
    return this.http.post<ProductDetailsDto>(this.apiUrl, product);
  }

  getProductByCode(productCode: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/code/${productCode}`);
  }
  
}
