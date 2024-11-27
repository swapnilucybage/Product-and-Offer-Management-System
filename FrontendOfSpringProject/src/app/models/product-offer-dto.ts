export class ProductOfferDTO {
    id?: number;
    productCode!: string;
    offerCode!: string;
    offerDetails?: OfferDetailsDTO;
    offerStartDate!: string;
    offerEndDate!: string;
  }
  
  export class OfferDetailsDTO {
    id?: number;
    code!: string;
    description: string | undefined;
    value?: string;
    status?: string;
    activationDate?: string;
    expiryDate?: string;
  }
  