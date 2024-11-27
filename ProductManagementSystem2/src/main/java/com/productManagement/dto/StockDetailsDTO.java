package com.productManagement.dto;

import java.util.List;

import lombok.Data;

@Data
public class StockDetailsDTO {

    private Long id;
    private Integer stockCount;
    private List<ProductDetailsDTO> products;
}