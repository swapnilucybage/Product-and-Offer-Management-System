package com.productManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.entity.StockDetails;

@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Long> {
		
	// Find stock details by product code (which is unique)
    Optional<StockDetails> findByProductCode(String productCode);
    
    // Find stock details for multiple product codes
    List<StockDetails> findByProductCodeIn(List<String> productCodes);
}
