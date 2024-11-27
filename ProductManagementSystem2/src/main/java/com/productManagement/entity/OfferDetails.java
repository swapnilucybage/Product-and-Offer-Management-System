package com.productManagement.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.productManagement.enums.OfferStatus;
import com.productManagement.enums.OfferValue;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "offer_details")
@JsonIgnoreProperties({"productOffers"}) // Ignore this during serialization
public class OfferDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferValue value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus status;

    @Column(name = "activation_date", nullable = false)
    private LocalDate activationDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<ProductOffer> productOffers;
}
