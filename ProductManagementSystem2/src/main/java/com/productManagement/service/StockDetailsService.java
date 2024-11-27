package com.productManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productManagement.dto.ProductDetailsDTO;
import com.productManagement.dto.StockDetailsDTO;
import com.productManagement.entity.ProductDetails;
import com.productManagement.entity.StockDetails;
import com.productManagement.exception.ResourceNotFoundException;
import com.productManagement.helper.Mapper;
import com.productManagement.repository.ProductDetailsRepository;
import com.productManagement.repository.StockDetailsRepository;

@Service
public class StockDetailsService {

    @Autowired
    private StockDetailsRepository stockDetailsRepository;
    
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    
    @Autowired
    private Mapper mapper;

 // Create or Update Stock Details
    public StockDetailsDTO saveOrUpdateStock(StockDetailsDTO stockDetailsDto) {
        // Fetch products based on the provided product IDs
        List<ProductDetails> products = productDetailsRepository.findAllById(
            stockDetailsDto.getProducts().stream()
            .map(ProductDetailsDTO::getId)  // Assuming ProductDetailsDTO has an ID field
            .collect(Collectors.toList())
        );

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("No products found for the provided IDs");
        }

        // Convert DTO to entity
        StockDetails stockDetails = mapper.stockDetailsDtoToEntity(stockDetailsDto);
        stockDetails.setProducts(products); // Set the related products

        // Update stock count for each product
        for (ProductDetails product : products) {
            product.setStockCount(stockDetailsDto.getStockCount()); // Update the stock count in ProductDetails
        }

        // Save stock details
        StockDetails savedStock = stockDetailsRepository.save(stockDetails);
        return mapper.stockDetailsEntityToDto(savedStock);
    }


    // Get All Stock Details
    public List<StockDetailsDTO> getAllStockDetails() {
        return stockDetailsRepository.findAll()
                .stream()
                .map(mapper::stockDetailsEntityToDto)
                .collect(Collectors.toList());
    }

    // Get Stock by Product Code
    public StockDetailsDTO getStockByProductCode(String productCode) {
        StockDetails stockDetails = stockDetailsRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found for product code: " + productCode));
        return mapper.stockDetailsEntityToDto(stockDetails);
    }
    
    // Get Stock by ID
    public StockDetailsDTO getStockById(Long id) {
        StockDetails stockDetails = stockDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID: " + id));
        return mapper.stockDetailsEntityToDto(stockDetails);
    }
    
    
    // Delete Stock Details by ID
    public void deleteStock(Long id) {
        if (!stockDetailsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stock not found with ID: " + id);
        }
        
        stockDetailsRepository.deleteById(id);
    }
}
