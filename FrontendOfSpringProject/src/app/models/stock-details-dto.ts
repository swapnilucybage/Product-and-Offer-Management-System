

// src/app/models/stock-details-dto.ts
export interface StockDetailsDto {
    id: number;
    stockCount: number;
    products: { id: number }[];
      // List of product IDs
  }

