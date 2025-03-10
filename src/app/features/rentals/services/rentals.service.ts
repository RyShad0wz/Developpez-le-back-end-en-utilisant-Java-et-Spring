import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Rental } from 'src/app/features/rentals/interfaces/rental.interface';
import { RentalResponse } from '../interfaces/api/rentalResponse.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RentalsService {

  private rentalsUrl = `${environment.baseUrl}/api/rentals`;

  constructor(private httpClient: HttpClient) { }

  // Retourne directement un Observable de Rental[]
  public all(): Observable<Rental[]> {
    return this.httpClient.get<Rental[]>(this.rentalsUrl).pipe(
      catchError(error => {
        console.error('Erreur lors de la récupération des rentals', error);
        return throwError(error);
      })
    );
  }

  public detail(id: string): Observable<Rental> {
    return this.httpClient.get<Rental>(`${this.rentalsUrl}/${id}`).pipe(
      catchError(error => {
        console.error(`Erreur lors de la récupération du rental avec id ${id}`, error);
        return throwError(error);
      })
    );
  }

  public create(rental: any): Observable<RentalResponse> {
    return this.httpClient.post<RentalResponse>(this.rentalsUrl, rental).pipe(
      catchError(error => {
        console.error('Erreur lors de la création du rental', error);
        return throwError(error);
      })
    );
  }

  public update(id: string, rental: any): Observable<RentalResponse> {
    return this.httpClient.put<RentalResponse>(`${this.rentalsUrl}/${id}`, rental).pipe(
      catchError(error => {
        console.error(`Erreur lors de la mise à jour du rental avec id ${id}`, error);
        return throwError(error);
      })
    );
  }
}
