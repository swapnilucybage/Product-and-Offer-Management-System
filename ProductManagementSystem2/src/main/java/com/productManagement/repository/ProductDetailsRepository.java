package com.productManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productManagement.entity.ProductDetails;
import com.productManagement.enums.Category;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	
	Optional<ProductDetails> findByCode(String code);
	
	// Find products by category
	List<ProductDetails> findByCategory(Category category);
}
