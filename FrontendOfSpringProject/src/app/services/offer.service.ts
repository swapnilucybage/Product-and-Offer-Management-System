import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OfferDetailsDto } from '../models/offer-details-dto';

@Injectable({
  providedIn: 'root'
})
export class OfferService {
  private apiUrl = 'http://localhost:8080/api/offers'; // Replace with your Spring Boot API base URL

  constructor(private http: HttpClient) {}

  getAllOffers(): Observable<OfferDetailsDto[]> {
    return this.http.get<OfferDetailsDto[]>(this.apiUrl);
  }

  getOfferById(id: number): Observable<OfferDetailsDto> {
    return this.http.get<OfferDetailsDto>(`${this.apiUrl}/${id}`);
  }

  deleteOffer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  saveOrUpdateOffer(offer: OfferDetailsDto): Observable<OfferDetailsDto> {
    return this.http.post<OfferDetailsDto>(this.apiUrl, offer);
  }
}
