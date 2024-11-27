package com.productManagement.dto;

import java.time.LocalDate;
import java.util.List;

import com.productManagement.enums.Category;

import lombok.Data;

@Data
public class ProductDetailsDTO {

    private Long id;
    private String code;
    private String name;
    private Category category;
    private LocalDate mfgDate;
    private LocalDate expDate;
    private Integer stockCount;
    private List<ProductOfferDTO> productOffers;

}

