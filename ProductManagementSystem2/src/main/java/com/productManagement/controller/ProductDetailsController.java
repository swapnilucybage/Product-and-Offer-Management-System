package com.productManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.dto.ProductDetailsDTO;
import com.productManagement.service.ProductDetailsService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductDetailsController {

    @Autowired
    private ProductDetailsService productDetailsService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDetailsDTO>> getAllProducts() {
        List<ProductDetailsDTO> products = productDetailsService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDTO> getProductById(@PathVariable Long id) {
        ProductDetailsDTO product = productDetailsService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Get product by code
    @GetMapping("/code/{code}")
    public ResponseEntity<ProductDetailsDTO> getProductByCode(@PathVariable String code) {
        ProductDetailsDTO product = productDetailsService.getProductByCode(code);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Create a new product or update an existing one
    @PostMapping
    public ResponseEntity<ProductDetailsDTO> saveOrUpdateProduct(@RequestBody ProductDetailsDTO productDetailsDto) {
        ProductDetailsDTO savedProduct = productDetailsService.saveOrUpdateProduct(productDetailsDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailsDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDetailsDTO productDetailsDto) {
        productDetailsDto.setId(id);  // Ensure the product ID is set for updating
        ProductDetailsDTO updatedProduct = productDetailsService.saveOrUpdateProduct(productDetailsDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productDetailsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

