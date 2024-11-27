// OfferDetails interface for each offer's detailed information
export interface OfferDetails {
    id: number;
    code: string;
    description: string;
    value: string; // Adjust to your enum type if needed
    status: string; // Adjust to your enum type if needed
    activationDate: string; // Use Date if needed
    expiryDate: string;
}

// ProductOffer interface to represent the offers linked to the product
export interface ProductOffer {
    id: number;
    productCode: string;
    offerCode: string;
    offerDetails: OfferDetails;
    offerStartDate: string;
    offerEndDate: string;
}

// ProductDetailsDto to include product information and its offers
export interface ProductDetailsDto {
    id: number;
    code: string;
    name: string;
    category: string; // Adjust to your enum type if needed
    mfgDate: string; // Use Date if needed
    expDate: string;
    stockCount: number;
    productOffers: ProductOffer[]; // Array of associated offers
}
