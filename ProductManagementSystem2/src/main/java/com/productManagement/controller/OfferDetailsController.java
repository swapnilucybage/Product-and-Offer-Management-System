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

import com.productManagement.dto.OfferDetailsDTO;
import com.productManagement.enums.OfferStatus;
import com.productManagement.service.OfferDetailsService;

@RestController
@RequestMapping("/api/offers")
public class OfferDetailsController {

    @Autowired
    private OfferDetailsService offerDetailsService;

    // Get all offers
    @GetMapping
    public ResponseEntity<List<OfferDetailsDTO>> getAllOffers() {
        List<OfferDetailsDTO> offers = offerDetailsService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    // Get offer by ID
    @GetMapping("/{id}")
    public ResponseEntity<OfferDetailsDTO> getOfferById(@PathVariable Long id) {
        OfferDetailsDTO offer = offerDetailsService.getOfferById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    // Get offer by code
    @GetMapping("/code/{code}")
    public ResponseEntity<OfferDetailsDTO> getOfferByCode(@PathVariable String code) {
        OfferDetailsDTO offer = offerDetailsService.getOfferByCode(code);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    // Get offers by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OfferDetailsDTO>> getOffersByStatus(@PathVariable OfferStatus status) {
        List<OfferDetailsDTO> offers = offerDetailsService.getOffersByStatus(status);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    // Create or Update an offer
    @PostMapping
    public ResponseEntity<OfferDetailsDTO> saveOrUpdateOffer(@RequestBody OfferDetailsDTO offerDetailsDto) {
        OfferDetailsDTO savedOffer = offerDetailsService.saveOrUpdateOffer(offerDetailsDto);
        return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
    }

    // Update offer statuses (PENDING -> ACTIVE, ACTIVE -> EXPIRED)
    @PutMapping("/updateStatuses")
    public ResponseEntity<Void> updateOfferStatuses() {
        offerDetailsService.updateOfferStatuses();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete an offer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerDetailsService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
