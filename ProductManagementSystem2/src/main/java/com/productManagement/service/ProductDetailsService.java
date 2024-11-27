package com.productManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.dto.ProductDetailsDTO;
import com.productManagement.entity.ProductDetails;
import com.productManagement.enums.Category;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.helper.Mapper;
import com.productManagement.repository.ProductDetailsRepository;

@Service
public class ProductDetailsService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private Mapper mapper;

    // Create or Update Product
    public ProductDetailsDTO saveOrUpdateProduct(ProductDetailsDTO productDetailsDTO) {
        // Convert DTO to Entity
        ProductDetails productDetails = mapper.productDtoToEntity(productDetailsDTO);
        
        // Nullify the stockDetails to avoid cascading on creation or update
//        productDetails.setStockDetails(null);
        
        // Validate category-specific rules
        validateProductDetails(productDetails);

        // Save the product entity to the repository
        ProductDetails savedProduct = productDetailsRepository.save(productDetails);
        
        System.out.println("The product are:" + productDetails);
        // Convert saved entity back to DTO
        return mapper.productEntityToDto(savedProduct);
    }

    // Get all Products
    public List<ProductDetailsDTO> getAllProducts() {
        return productDetailsRepository.findAll()
                .stream()
                .map(mapper::productEntityToDto) // Use Mapper to convert entities to DTOs
                .collect(Collectors.toList());
    }

    // Get Product by ID
    public ProductDetailsDTO getProductById(Long id) {
        ProductDetails product = productDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        return mapper.productEntityToDto(product);
    }

    // Get Product by Code
    public ProductDetailsDTO getProductByCode(String code) {
        ProductDetails product = productDetailsRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with Code: " + code));
        return mapper.productEntityToDto(product);
    }

    // Delete Product by ID
    public void deleteProduct(Long id) {
        if (!productDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        productDetailsRepository.deleteById(id);
    }

    // Helper method to validate product details based on category
    private void validateProductDetails(ProductDetails productDetails) {
        // Validate that CONSUMABLE products require an expiry date
        if (productDetails.getCategory() == Category.CONSUMABLE && productDetails.getExpDate() == null) {
            throw new IllegalArgumentException("Expiry date is required for CONSUMABLE products");
        }

        // Validate that mfgDate is before expDate for CONSUMABLE products
        if (productDetails.getCategory() == Category.CONSUMABLE 
            && productDetails.getMfgDate() != null 
            && productDetails.getExpDate() != null 
            && productDetails.getMfgDate().isAfter(productDetails.getExpDate())) {
            throw new IllegalArgumentException("Manufacture Date must be before the expiry Date");
        }
    }
}
