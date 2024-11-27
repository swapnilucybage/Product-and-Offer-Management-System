package com.productManagement.service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.dto.OfferDetailsDTO;
import com.productManagement.entity.OfferDetails;
import com.productManagement.enums.OfferStatus;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.helper.Mapper;
import com.productManagement.repository.OfferDetailsRepository;

@Service
public class OfferDetailsService {

    @Autowired
    private OfferDetailsRepository offerDetailsRepository;

    @Autowired
    private Mapper mapper;

    // Create or update offer
    public OfferDetailsDTO saveOrUpdateOffer(OfferDetailsDTO offerDetailsDto) {
        // Validate that activation date is before expiry date
        if (offerDetailsDto.getActivationDate().isAfter(offerDetailsDto.getExpiryDate())) {
            throw new IllegalArgumentException("Activation date must be before the expiry date");
        }

        // Automatically set offer status based on activation and expiry dates
        LocalDate today = LocalDate.now();
        if (offerDetailsDto.getActivationDate().isBefore(today) && offerDetailsDto.getExpiryDate().isAfter(today)) {
            offerDetailsDto.setStatus(OfferStatus.ACTIVE);
        } else if (offerDetailsDto.getExpiryDate().isBefore(today)) {
            offerDetailsDto.setStatus(OfferStatus.EXPIRED);
        } else {
            offerDetailsDto.setStatus(OfferStatus.PENDING);
        }

        // Convert DTO to entity and save it
        OfferDetails offerDetails = mapper.offerDetailsDtoToEntity(offerDetailsDto);
        OfferDetails savedOffer = offerDetailsRepository.save(offerDetails);

        // Return the saved entity as a DTO
        return mapper.offerDetailsEntityToDto(savedOffer);
    }

    // Get all offers
    public List<OfferDetailsDTO> getAllOffers() {
        return offerDetailsRepository.findAll()
                .stream()
                .map(mapper::offerDetailsEntityToDto)
                .collect(Collectors.toList());
    }

    // Get offer by ID
    public OfferDetailsDTO getOfferById(Long id) {
        OfferDetails offer = offerDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with ID: " + id));
        return mapper.offerDetailsEntityToDto(offer);
    }

    // Get offer by code
    public OfferDetailsDTO getOfferByCode(String code) {
        OfferDetails offer = offerDetailsRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found with code: " + code));
        return mapper.offerDetailsEntityToDto(offer);
    }

    // Get all offers by status
    public List<OfferDetailsDTO> getOffersByStatus(OfferStatus status) {
        return offerDetailsRepository.findByStatus(status)
                .stream()
                .map(mapper::offerDetailsEntityToDto)
                .collect(Collectors.toList());
    }

    // Update offer statuses based on activation and expiry dates
    public void updateOfferStatuses() {
        LocalDate today = LocalDate.now();

        // Activate pending offers
        List<OfferDetails> pendingOffers = offerDetailsRepository
                .findByStatusAndActivationDateBefore(OfferStatus.PENDING, today);
        for (OfferDetails offer : pendingOffers) {
            if (offer.getActivationDate().isBefore(today) && offer.getExpiryDate().isAfter(today)) {
                offer.setStatus(OfferStatus.ACTIVE);
                offerDetailsRepository.save(offer);
            }
        }

        // Expire active offers whose expiry date has passed
        List<OfferDetails> activeOffers = offerDetailsRepository.findByStatus(OfferStatus.ACTIVE);
        for (OfferDetails offer : activeOffers) {
            if (offer.getExpiryDate().isBefore(today)) {
                offer.setStatus(OfferStatus.EXPIRED);
                offerDetailsRepository.save(offer);
            }
        }
    }

    // Delete offer by ID
    public void deleteOffer(Long id) {
        if (!offerDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
        offerDetailsRepository.deleteById(id);
    }
}

