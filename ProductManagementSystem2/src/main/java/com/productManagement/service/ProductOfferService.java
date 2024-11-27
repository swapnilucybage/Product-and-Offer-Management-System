package com.productManagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.dto.ProductOfferDTO;
import com.productManagement.entity.OfferDetails;
import com.productManagement.entity.ProductDetails;
import com.productManagement.entity.ProductOffer;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.helper.Mapper;
import com.productManagement.repository.OfferDetailsRepository;
import com.productManagement.repository.ProductDetailsRepository;
import com.productManagement.repository.ProductOfferRepository;

@Service
public class ProductOfferService {

	@Autowired
	private ProductOfferRepository productOfferRepository;

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Autowired
	private OfferDetailsRepository offerDetailsRepository;

	@Autowired
	private Mapper mapper;

	// Create or Update Product Offer
	public ProductOfferDTO saveOrUpdateProductOffer(ProductOfferDTO productOfferDTO) {

		// Fetch or create ProductDetails
		ProductDetails product = productDetailsRepository.findByCode(productOfferDTO.getProductCode())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Product with code not found: " + productOfferDTO.getProductCode()));

		// Fetch or create OfferDetails
		OfferDetails offer = offerDetailsRepository.findByCode(productOfferDTO.getOfferCode()).orElseThrow(
				() -> new ResourceNotFoundException("Offer with code not found: " + productOfferDTO.getOfferCode()));

		// Convert DTO to entity
		ProductOffer productOffer = mapper.productOfferDtoToEntity(productOfferDTO);

		// Set relationships
		productOffer.setProduct(product); // Set the related product
		productOffer.setOffer(offer); // Set the related offer

		// Validate that the offer start date is before the end date
		if (productOffer.getOfferStartDate().isAfter(productOffer.getOfferEndDate())) {
			throw new IllegalArgumentException("Offer start date must be before the end date");
		}

		// Save the ProductOffer
		ProductOffer savedProductOffer = productOfferRepository.save(productOffer);

		// Convert the saved entity back to DTO and return
		return mapper.productOfferEntityToDto(savedProductOffer);
	}

	// Get all product offers
	public List<ProductOfferDTO> getAllProductOffers() {
		return productOfferRepository.findAll().stream().map(mapper::productOfferEntityToDto)
				.collect(Collectors.toList());
	}

	// Get valid offers for a product (startDate and endDate Validation)
	public List<ProductOfferDTO> getValidProductOffers(String productCode) {
		LocalDate today = LocalDate.now();
		return productOfferRepository
				.findByProductCodeAndOfferStartDateBeforeAndOfferEndDateAfter(productCode, today, today).stream()
				.map(mapper::productOfferEntityToDto).collect(Collectors.toList());
	}
	
	public List<String> getAllProductCodes() {
	    return productDetailsRepository.findAll().stream()
	        .map(ProductDetails::getCode)
	        .collect(Collectors.toList());
	}

	public List<String> getAllOfferCodes() {
	    return offerDetailsRepository.findAll().stream()
	        .map(OfferDetails::getCode)
	        .collect(Collectors.toList());
	}
	
	// Get product offer by ID
	public ProductOfferDTO getProductOfferById(Long id) {
		ProductOffer productOffer = productOfferRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ProductOffer with ID " + id + " not found"));
		return mapper.productOfferEntityToDto(productOffer);
	}

	// Delete product offer by ID
	public void deleteProductOffer(Long id) {
		productOfferRepository.deleteById(id);
	}
}
