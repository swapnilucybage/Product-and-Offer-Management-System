import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductOfferDTO } from '../models/product-offer-dto';

@Injectable({
  providedIn: 'root'
})
export class ProductOfferService {
  private apiUrl = 'http://localhost:8080/api/productOffers';

  constructor(private http: HttpClient) {}

  getAllProductOffers(): Observable<ProductOfferDTO[]> {
    return this.http.get<ProductOfferDTO[]>(this.apiUrl);
  }

  getProductOfferById(id: number): Observable<ProductOfferDTO> {
    return this.http.get<ProductOfferDTO>(`${this.apiUrl}/${id}`);
  }

  createProductOffer(productOffer: ProductOfferDTO): Observable<ProductOfferDTO> {
    return this.http.post<ProductOfferDTO>(this.apiUrl, productOffer);
  }

  getProductCodes(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/getproductCodes`);
  }
  
  getOfferCodes(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/getofferCodes`);
  }  

  updateProductOffer(id: number, productOffer: ProductOfferDTO): Observable<ProductOfferDTO> {
    return this.http.put<ProductOfferDTO>(`${this.apiUrl}/${id}`, productOffer);
  }

  deleteProductOffer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
