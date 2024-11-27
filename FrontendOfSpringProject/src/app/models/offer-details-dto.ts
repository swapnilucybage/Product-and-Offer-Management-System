
export interface OfferDetailsDto {
    id: number;
    code: string;
    description: string;
    value: string; // Adjust based on your enum types if necessary
    status: string; // Adjust based on your enum types if necessary
    activationDate: string; // Use Date if needed
    expiryDate: string;
  }
  