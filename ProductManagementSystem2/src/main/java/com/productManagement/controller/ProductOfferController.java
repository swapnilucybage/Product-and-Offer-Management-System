package com.productManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.dto.ProductOfferDTO;
import com.productManagement.service.ProductOfferService;

@RestController
@RequestMapping("/api/productOffers")
public class ProductOfferController {

	@Autowired
	private ProductOfferService productOfferService;

	// Get all product offers
	@GetMapping
	public ResponseEntity<List<ProductOfferDTO>> getAllProductOffers() {
		List<ProductOfferDTO> offers = productOfferService.getAllProductOffers();
		return new ResponseEntity<>(offers, HttpStatus.OK);
	}

	// Get product offer by ID
	@GetMapping("/{id}")
	public ResponseEntity<ProductOfferDTO> getProductOfferById(@PathVariable Long id) {
		ProductOfferDTO productOfferDto = productOfferService.getProductOfferById(id);
		return new ResponseEntity<>(productOfferDto, HttpStatus.OK);
	}

	// Get valid offers for a product
	@GetMapping("/valid/{productCode}")
	public ResponseEntity<List<ProductOfferDTO>> getValidProductOffers(@PathVariable String productCode) {
		List<ProductOfferDTO> validOffers = productOfferService.getValidProductOffers(productCode);
		return new ResponseEntity<>(validOffers, HttpStatus.OK);
	}

	// Create or update product offer
	@PostMapping
	public ResponseEntity<ProductOfferDTO> createProductOffer(@RequestBody ProductOfferDTO productOfferDto) {
		ProductOfferDTO savedOffer = productOfferService.saveOrUpdateProductOffer(productOfferDto);
		return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
	}

	// Update product offer
	@PutMapping("/{id}")
	public ResponseEntity<ProductOfferDTO> updateProductOffer(@PathVariable Long id,
			@RequestBody ProductOfferDTO productOfferDto) {
		productOfferDto.setId(id); // Set the ID from the path variable
		ProductOfferDTO updatedOffer = productOfferService.saveOrUpdateProductOffer(productOfferDto);
		return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
	}
	
	// Get all product codes
	@GetMapping("/getproductCodes")
	public ResponseEntity<List<String>> getAllProductCodes() {
	    List<String> productCodes = productOfferService.getAllProductCodes();
	    return new ResponseEntity<>(productCodes, HttpStatus.OK);
	}

	// Get all offer codes
	@GetMapping("/getofferCodes")
	public ResponseEntity<List<String>> getAllOfferCodes() {
	    List<String> offerCodes = productOfferService.getAllOfferCodes();
	    return new ResponseEntity<>(offerCodes, HttpStatus.OK);
	}

	// Delete product offer by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductOffer(@PathVariable Long id) {
		productOfferService.deleteProductOffer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
