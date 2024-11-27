package com.productManagement.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "stock_details")
public class StockDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String productCode;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_stock",
        joinColumns = @JoinColumn(name = "stock_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductDetails> products;

    @Column(name = "stock_count", nullable = false)
    private Integer stockCount;
    
}