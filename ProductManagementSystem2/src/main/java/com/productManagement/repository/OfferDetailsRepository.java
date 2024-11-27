package com.productManagement.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.productManagement.entity.OfferDetails;
import com.productManagement.enums.OfferStatus;

@Repository
public interface OfferDetailsRepository extends JpaRepository<OfferDetails, Long> {
	
	Optional<OfferDetails> findByCode(String code);
	
	List<OfferDetails> findByStatus(OfferStatus status);
	
	List<OfferDetails> findByStatusAndActivationDateBefore(OfferStatus status, LocalDate today);
	
	List<OfferDetails> findByExpiryDateBefore(LocalDate today);
	
	// Find active offers
	List<OfferDetails> findByActivationDateBeforeAndExpiryDateAfter(LocalDate today, LocalDate today1);

	// Find offers by status and expiry date
	List<OfferDetails> findByStatusAndExpiryDateAfter(OfferStatus status, LocalDate today);
}
