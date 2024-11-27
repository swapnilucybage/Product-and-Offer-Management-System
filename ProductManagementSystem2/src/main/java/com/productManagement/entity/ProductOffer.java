package com.productManagement.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product_offer")
@JsonIgnoreProperties({"product", "offer"}) // Ignore these during serialization
public class ProductOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDetails product;
    
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferDetails offer;

    @Column(name = "offer_start_date", nullable = false)
    private LocalDate offerStartDate;

    @Column(name = "offer_end_date", nullable = false)
    private LocalDate offerEndDate;
    
}

