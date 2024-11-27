package com.productManagement.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductOfferDTO {

	private Long id;
	private String productCode;
	private String offerCode;
	private OfferDetailsDTO offerDetails; // Complete offer details
//    private ProductDetailsDTO productDetails;
	private LocalDate offerStartDate;
	private LocalDate offerEndDate;

}
