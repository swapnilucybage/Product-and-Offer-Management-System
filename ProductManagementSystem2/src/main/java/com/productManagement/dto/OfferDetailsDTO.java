package com.productManagement.dto;

import java.time.LocalDate;

import com.productManagement.enums.OfferStatus;
import com.productManagement.enums.OfferValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDetailsDTO {

    private Long id;
    private String code;
    private String description;
    private OfferValue value;
    private OfferStatus status;
    private LocalDate activationDate;
    private LocalDate expiryDate;

}

