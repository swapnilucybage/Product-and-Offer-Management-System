package com.productManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productManagement.dto.StockDetailsDTO;
import com.productManagement.service.StockDetailsService;

@RestController
@RequestMapping("/api/stock")
public class StockDetailsController {

    @Autowired
    private StockDetailsService stockDetailsService;

    // Get all stock details
    @GetMapping
    public ResponseEntity<List<StockDetailsDTO>> getAllStockDetails() {
        List<StockDetailsDTO> stockDetails = stockDetailsService.getAllStockDetails();
        return new ResponseEntity<>(stockDetails, HttpStatus.OK);
    }

    // Get stock by product code
    @GetMapping("/product/{productCode}")
    public ResponseEntity<StockDetailsDTO> getStockByProductCode(@PathVariable String productCode) {
        StockDetailsDTO stockDetails = stockDetailsService.getStockByProductCode(productCode);
        return new ResponseEntity<>(stockDetails, HttpStatus.OK);
    }

    // Create or update stock details
    @PostMapping
    public ResponseEntity<StockDetailsDTO> saveOrUpdateStock(@RequestBody StockDetailsDTO stockDetailsDto) {
        StockDetailsDTO savedStock = stockDetailsService.saveOrUpdateStock(stockDetailsDto);
        return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
    }
    
    // Get stock by ID
    @GetMapping("/{id}")
    public ResponseEntity<StockDetailsDTO> getStockById(@PathVariable Long id) {
        StockDetailsDTO stockDetails = stockDetailsService.getStockById(id);
        return new ResponseEntity<>(stockDetails, HttpStatus.OK);
    }

    // Delete stock by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockDetailsService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
